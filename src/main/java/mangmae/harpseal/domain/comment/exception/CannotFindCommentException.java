package mangmae.harpseal.domain.comment.exception;

public class CannotFindCommentException extends RuntimeException {
    public CannotFindCommentException() {
        super();
    }

    public CannotFindCommentException(Long id) {
        super(String.format("cannot find comment id=[%d]", id));
    }

    public CannotFindCommentException(String message) {
        super(message);
    }

    public CannotFindCommentException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotFindCommentException(Throwable cause) {
        super(cause);
    }

    protected CannotFindCommentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
