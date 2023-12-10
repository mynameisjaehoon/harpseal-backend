package mangmae.harpseal.domain.exception;

public class CannotFindQuestionTypeException extends RuntimeException {
    public CannotFindQuestionTypeException() {
        super();
    }

    public CannotFindQuestionTypeException(String message) {
        super(message);
    }

    public CannotFindQuestionTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotFindQuestionTypeException(Throwable cause) {
        super(cause);
    }

    protected CannotFindQuestionTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
