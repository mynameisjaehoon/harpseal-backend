package mangmae.harpseal.domain.quiz.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mangmae.harpseal.domain.app.QuizFacadeService;
import mangmae.harpseal.domain.quiz.dto.request.QuizCreateRequestForm;
import mangmae.harpseal.domain.quiz.dto.request.QuizSearchRequestCond;
import mangmae.harpseal.domain.quiz.service.QuizService;
import mangmae.harpseal.entity.Quiz;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.net.URI;

@RestController
@RequestMapping("api/v1/quiz")
@RequiredArgsConstructor
@Slf4j
public class QuizController {

    private final QuizService quizService;
    private final QuizFacadeService quizFacadeService;

    /**
     * 퀴즈 리스트를 반환하는 컨트롤러 메서드
     *
     * @return 퀴즈 리스트 DTO
     */
    @GetMapping()
    public String quizList(
        @RequestBody(required = false) QuizSearchRequestCond condition,
        Pageable pageable
    ) {
        quizService.find(condition.toServiceDto());

        return "ok";
    }

    @PostMapping(value = "/new", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> createQuiz(
            @RequestPart(value = "form") QuizCreateRequestForm form,
            @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail
    ) {
        Quiz createdQuiz = quizFacadeService.createQuiz(form.toServiceDto(), thumbnail);
        return ResponseEntity.created(URI.create("/quiz/api/v1/" + createdQuiz.getId())).build();
    }


}
