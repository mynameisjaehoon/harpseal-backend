package mangmae.harpseal.domain.quiz.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mangmae.harpseal.domain.application.QuizFacadeService;
import mangmae.harpseal.domain.quiz.dto.QuizDeleteRequestDto;
import mangmae.harpseal.domain.quiz.dto.QuizEditRequestDto;
import mangmae.harpseal.domain.quiz.application.dto.*;
import mangmae.harpseal.domain.quiz.dto.QuizSearchType;
import mangmae.harpseal.domain.quiz.api.dto.question.QuestionCreateRequestForm;
import mangmae.harpseal.domain.quiz.api.dto.question.QuestionCreateResponseDto;
import mangmae.harpseal.domain.quiz.dto.QuizCreateRequestForm;
import mangmae.harpseal.domain.quiz.dto.QuizSearchRequestCond;
import mangmae.harpseal.domain.quiz.application.QuizService;
import mangmae.harpseal.global.entity.Question;
import mangmae.harpseal.global.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.midi.VoiceStatus;
import java.net.URI;

import static org.springframework.http.MediaType.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/quiz")
@RequiredArgsConstructor
@Slf4j
public class QuizController {

    private final QuizService quizService;
    private final QuizFacadeService quizFacadeService;

    /**
     * 퀴즈 리스트 조회 핸들러
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

    /**
     * 퀴즈 생성 핸들러
     * @param form 퀴즈 생성 폼
     * @param thumbnail 퀴즈 썸네일 이미지 데이터
     * @return 퀴즈 생성 응답 HTTP 201 CREATED
     */
    @PostMapping(value = "/new", consumes = {APPLICATION_JSON_VALUE, MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> createQuiz(
        @RequestPart(value = "form") QuizCreateRequestForm form,
        @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail
    ) {
        Quiz createdQuiz = quizFacadeService.createQuiz(form.toServiceDto(), thumbnail);
        return ResponseEntity
            .created(URI.create("/api/v1/quiz/" + createdQuiz.getId()))
            .build();
    }

    /**
     * 퀴즈 단일 조회 핸들러
     * @param quizId 조회 퀴즈 ID
     * @return 단일 조회 퀴즈 정보, HTTP 200 OK
     */
    @GetMapping("/{quizId}")
    public ResponseEntity<SingleQuizServiceResponse> findSingleQuiz(@PathVariable("quizId") Long quizId) {
        SingleQuizServiceResponse result = quizFacadeService.findSingleQuiz(new SingleQuizServiceCond(quizId));
        return ResponseEntity
            .ok()
            .body(result);
    }

    /**
     * 문제 생성 핸들러
     * @param quizId 문제 생성할 퀴즈 ID
     * @param form 문제 생성 폼
     * @param attachment 문제 첨부파일 데이터
     * @return 생성된 퀴즈 정보, HTTP 201 CREATED
     */
    @PostMapping(value = "/{quizId}", consumes = {APPLICATION_JSON_VALUE, MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<QuestionCreateResponseDto> createQuestion(
        @PathVariable("quizId") Long quizId,
        @RequestPart(value = "form") QuestionCreateRequestForm form,
        @RequestPart(value = "attachment") MultipartFile attachment
    ) {
        Question storeQuestion = quizFacadeService.createQuestion(quizId, form.toServiceDto(), attachment);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new QuestionCreateResponseDto("문제 생성에 성공하였습니다."));
    }

    /**
     * 퀴즈 삭제 핸들러
     * @param quizId 삭제 퀴즈 ID
     * @param requestDto 퀴즈 삭제 폼
     * @return HTTP 303 SEE OTHER, 퀴즈 목록으로 리다이렉션
     */
    @DeleteMapping("/{quizId}")
    public ResponseEntity<Void> deleteQuiz(
        @PathVariable("quizId") Long quizId,
        @RequestBody QuizDeleteRequestDto requestDto
    ) {
        quizService.deleteQuizById(quizId, requestDto.getPassword());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://localhost:8080/api/v1/quiz"));
        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }

    /**
     * 퀴즈 좋아요 증가 핸들러
     * @param quizId 좋아요 증가할 퀴즈 ID
     * @return HTTP 200 OK
     */
    @PostMapping("/{quizId}/like")
    public ResponseEntity<Void> addQuizLike(@PathVariable Long quizId) {
        quizService.addQuizLike(quizId);
        return ResponseEntity.ok().build();
    }

    /**
     * 퀴즈 수정 핸들러
     * @param quizId 수정 퀴즈 ID
     * @param form 퀴즈 수정 폼
     * @param thumbnail 퀴즈 썸네일 수정 데이터
     * @return HTTP 303 SEE OTHER, {quizID} 단일 정보조회로 리다이렉션
     */
    @PutMapping(value = "/{quizId}/edit", consumes = {APPLICATION_JSON_VALUE, MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> editQuiz(
        @PathVariable("quizId") Long quizId,
        @RequestPart(value = "form") QuizEditRequestDto form,
        @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail
    ) {
        quizFacadeService.editQuiz(form.toServiceDto(quizId), thumbnail);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://localhost:8080/api/v1/quiz/" + quizId));
        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }


}
