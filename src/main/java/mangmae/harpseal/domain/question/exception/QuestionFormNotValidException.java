package mangmae.harpseal.domain.question.exception;

public class QuestionFormNotValidException extends RuntimeException {
    public QuestionFormNotValidException() {
        super();
    }

    public QuestionFormNotValidException(String message) {
        super(message);
    }

    public QuestionFormNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuestionFormNotValidException(Throwable cause) {
        super(cause);
    }

    protected QuestionFormNotValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
