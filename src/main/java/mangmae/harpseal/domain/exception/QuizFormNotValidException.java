package mangmae.harpseal.domain.exception;

public class QuizFormNotValidException extends RuntimeException {
    public QuizFormNotValidException() {
        super();
    }

    public QuizFormNotValidException(String message) {
        super(message);
    }

    public QuizFormNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuizFormNotValidException(Throwable cause) {
        super(cause);
    }

    protected QuizFormNotValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
