package mangmae.harpseal.domain.quiz.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mangmae.harpseal.domain.app.QuizFacadeService;
import mangmae.harpseal.domain.quiz.controller.dto.quiz.QuizDeleteRequestDto;
import mangmae.harpseal.domain.quiz.controller.dto.quiz.QuizEditRequestDto;
import mangmae.harpseal.domain.quiz.service.dto.quiz.*;
import mangmae.harpseal.domain.quiz.dto.QuizSearchType;
import mangmae.harpseal.domain.quiz.controller.dto.question.QuestionCreateRequestForm;
import mangmae.harpseal.domain.quiz.controller.dto.question.QuestionCreateResponseDto;
import mangmae.harpseal.domain.quiz.controller.dto.quiz.QuizCreateRequestForm;
import mangmae.harpseal.domain.quiz.controller.dto.quiz.QuizSearchRequestCond;
import mangmae.harpseal.domain.quiz.service.QuizService;
import mangmae.harpseal.entity.Question;
import mangmae.harpseal.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

import static org.springframework.http.MediaType.*;

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

    @GetMapping("/{quizId}")
    public SingleQuizServiceResponse findSingleQuiz(@PathVariable("quizId") Long quizId) {
        return quizService.findSingleQuiz(new SingleQuizServiceCond(quizId));
    }

    @PostMapping(value = "/{quizId}", consumes = {APPLICATION_JSON_VALUE, MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<QuestionCreateResponseDto> createQuestion(
        @PathVariable("quizId") Long quizId,
        @RequestPart(value = "form") QuestionCreateRequestForm form,
        @RequestPart(value = "attachment") MultipartFile attachment
    ) {
        Question storeQuestion = quizFacadeService.createQuestion(quizId, form.toServiceDto(), attachment);
        return ResponseEntity.status(HttpStatus.CREATED).body(new QuestionCreateResponseDto("문제 생성에 성공하였습니다."));
    }

    @PostMapping(value = "/new", consumes = {APPLICATION_JSON_VALUE, MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> createQuiz(
            @RequestPart(value = "form") QuizCreateRequestForm form,
            @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail
    ) {
        Quiz createdQuiz = quizFacadeService.createQuiz(form.toServiceDto(), thumbnail);
        return ResponseEntity
            .created(URI.create("/quiz/api/v1/" + createdQuiz.getId()))
            .build();
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<QuizDeleteResponseDto> deleteQuiz(
        @PathVariable("quizId") Long quizId,
        @RequestBody QuizDeleteRequestDto requestDto
    ) {
        QuizDeleteResponseDto response = quizService.deleteQuizById(quizId, requestDto.getPassword());
        return ResponseEntity
            .accepted()
            .body(response);
    }

    @PutMapping(value = "/{quizId}/edit", consumes = {APPLICATION_JSON_VALUE, MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<QuizEditServiceResponse> editQuiz(
        @PathVariable("quizId") Long quizId,
        @RequestPart(value = "form") QuizEditRequestDto form,
        @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail
    ) {
        QuizEditServiceResponse response = quizService.editQuiz(form.toServiceDto(quizId), thumbnail);
        return ResponseEntity
            .ok()
            .body(response);
    }
}
