package mangmae.harpseal.exception;

public class PasswordNotMatchException extends RuntimeException {
    public PasswordNotMatchException() {
        super();
    }

    public PasswordNotMatchException(String password1, String password2) {
        super("[" + password1 + "] and [" + password2 + "] is not match");
    }

    public PasswordNotMatchException(String message) {
        super(message);
    }

    public PasswordNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotMatchException(Throwable cause) {
        super(cause);
    }

    protected PasswordNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
