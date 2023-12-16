package mangmae.harpseal.domain.quiz.exhandler;


import mangmae.harpseal.domain.exception.CannotFindQuizException;
import mangmae.harpseal.domain.quiz.controller.QuizController;
import mangmae.harpseal.domain.quiz.exception.QuizPasswordNotMatchException;
import mangmae.harpseal.domain.quiz.exception.dto.QuizDeleteExceptionResponse;
import mangmae.harpseal.exception.PasswordNotMatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice(assignableTypes = {QuizController.class})
public class QuizControllerAdvice {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(CannotFindQuizException.class)
    public QuizDeleteExceptionResponse unknownQuizId(CannotFindQuizException ex) {
        return new QuizDeleteExceptionResponse(ex.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(PasswordNotMatchException.class)
    public QuizDeleteExceptionResponse passwordMatchFail(PasswordNotMatchException ex) {
        return new QuizDeleteExceptionResponse(ex.getMessage());
    }
}