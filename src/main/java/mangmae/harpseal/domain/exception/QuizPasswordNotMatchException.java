package mangmae.harpseal.domain.exception;

public class QuizPasswordNotMatchException extends RuntimeException {
    public QuizPasswordNotMatchException() {
        super();
    }

    public QuizPasswordNotMatchException(String message) {
        super(message);
    }

    public QuizPasswordNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuizPasswordNotMatchException(Throwable cause) {
        super(cause);
    }

    protected QuizPasswordNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
