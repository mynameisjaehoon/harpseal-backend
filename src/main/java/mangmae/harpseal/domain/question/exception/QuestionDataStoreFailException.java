package mangmae.harpseal.domain.question.exception;

public class QuestionDataStoreFailException extends RuntimeException {
    public QuestionDataStoreFailException() {
        super();
    }

    public QuestionDataStoreFailException(String message) {
        super(message);
    }

    public QuestionDataStoreFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuestionDataStoreFailException(Throwable cause) {
        super(cause);
    }

    protected QuestionDataStoreFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
