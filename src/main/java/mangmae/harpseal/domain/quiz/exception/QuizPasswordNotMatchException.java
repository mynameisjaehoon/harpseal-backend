package mangmae.harpseal.domain.quiz.exception;

import mangmae.harpseal.exception.PasswordNotMatchException;

public class QuizPasswordNotMatchException extends PasswordNotMatchException {
    public QuizPasswordNotMatchException() {
        super();
    }

    public QuizPasswordNotMatchException(Long quizId, String password, Throwable cause) {
        super("id [" + quizId + "] quiz password is invalid", cause);
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
