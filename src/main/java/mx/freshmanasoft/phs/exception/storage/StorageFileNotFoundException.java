package mx.freshmanasoft.phs.exception.storage;

public class StorageFileNotFoundException extends StorageException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3273375302328183979L;

	public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}