package mangmae.harpseal.domain.question.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mangmae.harpseal.domain.app.QuizFacadeService;
import mangmae.harpseal.domain.quiz.dto.request.QuestionCreateRequestForm;
import mangmae.harpseal.domain.quiz.dto.request.QuestionCreateResponseDto;
import mangmae.harpseal.entity.Question;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.*;


@Slf4j
@RestController
@RequestMapping("/api/v1/quiz")
@RequiredArgsConstructor
public class QuestionController {

    private final QuizFacadeService quizFacadeService;

    @PostMapping(value = "/{quizId}", consumes = {APPLICATION_JSON_VALUE, MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<QuestionCreateResponseDto> createQuestion(
        @PathVariable("quizId") Long quizId,
        @RequestPart(value = "form") QuestionCreateRequestForm form,
        @RequestPart(value = "attachment") MultipartFile attachment
    ) {
        log.info("QuestionController-createQuestion form={}", form);
        Question storeQuestion = quizFacadeService.createQuestion(quizId, form.toServiceDto(), attachment);
        return ResponseEntity.status(HttpStatus.CREATED).body(new QuestionCreateResponseDto());
    }
}
