package mangmae.harpseal.domain.exception;

public class CannotFindQuestionException extends RuntimeException {
    public CannotFindQuestionException() {
        super();
    }

    public CannotFindQuestionException(String message) {
        super(message);
    }

    public CannotFindQuestionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotFindQuestionException(Throwable cause) {
        super(cause);
    }

    protected CannotFindQuestionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
