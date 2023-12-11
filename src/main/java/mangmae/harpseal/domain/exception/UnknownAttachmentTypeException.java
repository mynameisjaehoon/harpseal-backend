package mangmae.harpseal.domain.exception;

public class UnknownAttachmentTypeException extends RuntimeException {
    public UnknownAttachmentTypeException() {
        super();
    }

    public UnknownAttachmentTypeException(String message) {
        super(message);
    }

    public UnknownAttachmentTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownAttachmentTypeException(Throwable cause) {
        super(cause);
    }

    protected UnknownAttachmentTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
