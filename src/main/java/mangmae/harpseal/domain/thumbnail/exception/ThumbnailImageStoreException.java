package mangmae.harpseal.domain.thumbnail.exception;

public class ThumbnailImageStoreException extends RuntimeException {
    public ThumbnailImageStoreException() {
        super();
    }

    public ThumbnailImageStoreException(String message) {
        super(message);
    }

    public ThumbnailImageStoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public ThumbnailImageStoreException(Throwable cause) {
        super(cause);
    }

    protected ThumbnailImageStoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
