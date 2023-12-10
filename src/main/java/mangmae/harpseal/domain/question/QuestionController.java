package mangmae.harpseal.domain.question;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mangmae.harpseal.domain.application.QuizFacadeService;
import mangmae.harpseal.domain.quiz.dto.QuestionCreateRequestForm;
import mangmae.harpseal.domain.quiz.dto.QuestionCreateResponseDto;
import mangmae.harpseal.entity.Question;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RestController("/api/v1/quiz")
@RequiredArgsConstructor
public class QuestionController {

    private final QuizFacadeService quizFacadeService;

    @PostMapping(value = "/{quizId}")
    public ResponseEntity<QuestionCreateResponseDto> createQuestion(
        @RequestParam("quizId") Long quizId,
        @RequestPart QuestionCreateRequestForm form,
        @RequestPart MultipartFile attachment
    ) {
        log.info("QuizController-createQuestion form={}", form);
        Question storeQuestion = quizFacadeService.createQuestion(quizId, form.toServiceDto(), attachment);
        return ResponseEntity.status(HttpStatus.CREATED).body(new QuestionCreateResponseDto());
    }
}
