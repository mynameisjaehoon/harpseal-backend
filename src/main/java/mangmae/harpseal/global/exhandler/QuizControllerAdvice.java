package mangmae.harpseal.global.exhandler;


import mangmae.harpseal.domain.comment.api.CommentController;
import mangmae.harpseal.domain.quiz.exception.CannotFindQuizException;
import mangmae.harpseal.domain.quiz.api.QuizController;
import mangmae.harpseal.domain.quiz.exception.dto.InvalidQuizInfoResponse;
import mangmae.harpseal.domain.quiz.exception.dto.QuizDeleteExceptionResponse;
import mangmae.harpseal.global.exception.PasswordNotMatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice(assignableTypes = {QuizController.class, CommentController.class})
public class QuizControllerAdvice {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(CannotFindQuizException.class)
    public InvalidQuizInfoResponse unknownQuizId(CannotFindQuizException ex) {
        return new InvalidQuizInfoResponse(ex.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(PasswordNotMatchException.class)
    public QuizDeleteExceptionResponse passwordMatchFail(PasswordNotMatchException ex) {
        return new QuizDeleteExceptionResponse(ex.getMessage());
    }
}
