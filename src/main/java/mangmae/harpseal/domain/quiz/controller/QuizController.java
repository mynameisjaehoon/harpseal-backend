package mangmae.harpseal.domain.quiz.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mangmae.harpseal.domain.app.QuizFacadeService;
import mangmae.harpseal.domain.quiz.dto.QuizSearchType;
import mangmae.harpseal.domain.quiz.dto.request.QuizCreateRequestForm;
import mangmae.harpseal.domain.quiz.controller.dto.QuizSearchRequestCond;
import mangmae.harpseal.domain.quiz.service.QuizService;
import mangmae.harpseal.domain.quiz.service.dto.QuizSearchServiceDto;
import mangmae.harpseal.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

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
    public Page<QuizSearchServiceDto> quizList(
        @RequestBody(required = false) QuizSearchRequestCond condition,
        Pageable pageable
    ) {
        if (condition == null) {
            condition = new QuizSearchRequestCond(null, QuizSearchType.NONE.toString());
        }
        return quizService.searchWithCondition(condition.toServiceDto(), pageable);
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
