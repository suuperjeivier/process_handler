package mx.freshmanasoft.phs.service.storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import mx.freshmanasoft.phs.config.properties.StorageProperties;
import mx.freshmanasoft.phs.exception.storage.StorageException;
import mx.freshmanasoft.phs.exception.storage.StorageFileNotFoundException;

@Service
public class FileSystemStorageService implements StorageService {
	private final static Logger logger = LoggerFactory.getLogger(FileSystemStorageService.class);
	
    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public String store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String substitute = UUID.randomUUID().toString() + "_" + filename;
        try {
            if (file.isEmpty()) {
            	logger.error(new StorageException("Failed to store empty file " + filename).getMessage());
            }
            if (filename.contains("..")) {
                // This is a security check
            	logger.error( new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename).getLocalizedMessage());
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(substitute),
                    StandardCopyOption.REPLACE_EXISTING);
                return substitute;
            }
        }
        catch (IOException e) {
        	logger.error(new StorageException("Failed to store file " + filename, e).getMessage());
        }
        return null;
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                .filter(path -> !path.equals(this.rootLocation))
                .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}