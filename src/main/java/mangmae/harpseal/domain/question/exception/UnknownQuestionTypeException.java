package mangmae.harpseal.domain.question.exception;

public class UnknownQuestionTypeException extends RuntimeException {
    public UnknownQuestionTypeException() {
        super();
    }

    public UnknownQuestionTypeException(String message) {
        super(message);
    }

    public UnknownQuestionTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownQuestionTypeException(Throwable cause) {
        super(cause);
    }

    protected UnknownQuestionTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
