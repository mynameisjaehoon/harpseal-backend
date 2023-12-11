package mangmae.harpseal.domain.exception;

public class CannotFindQuizException extends RuntimeException {
    public CannotFindQuizException() {
        super();
    }

    public CannotFindQuizException(String message) {
        super(message);
    }

    public CannotFindQuizException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotFindQuizException(Throwable cause) {
        super(cause);
    }

    protected CannotFindQuizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
