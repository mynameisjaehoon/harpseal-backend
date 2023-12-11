package mangmae.harpseal.domain.quiz.exception;

public class UnknownQuizSearchConditionException extends RuntimeException {
    public UnknownQuizSearchConditionException() {
        super();
    }

    public UnknownQuizSearchConditionException(String type) {
        super("Can not find [" + type + "] question type");
    }

    public UnknownQuizSearchConditionException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownQuizSearchConditionException(Throwable cause) {
        super(cause);
    }

    protected UnknownQuizSearchConditionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
