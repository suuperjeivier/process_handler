package mx.freshmanasoft.phs.exception.storage;


public class StorageException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 9217365208034847468L;

	public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}