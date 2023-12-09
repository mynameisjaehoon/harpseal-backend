package mangmae.harpseal.domain.quiz.controller;

import lombok.RequiredArgsConstructor;
import mangmae.harpseal.domain.dto.QuizRegistrationFormDto;
import mangmae.harpseal.domain.quiz.service.QuizService;
import mangmae.harpseal.entity.Quiz;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController("/quiz/api/v1")
@RequiredArgsConstructor
public class QuizController {

    @Value("${file.}")

    private final QuizService quizService;

    /**
     * 퀴즈 리스트를 반환하는 컨트롤러 메서드
     * @return 퀴즈 리스트 DTO
     */
    @GetMapping("/list")
    public String quizList() {
        return "ok";
    }

    @PostMapping(value = "/new", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> createQuiz(
            @RequestPart QuizRegistrationFormDto form,
            @RequestPart MultipartFile thumbnailImage
    ) {

        Quiz createdQuiz = quizService.createQuiz(form);
        quizService.storeQuizThumbnailImage(createdQuiz, thumbnailImage);
        return ResponseEntity.created(URI.create("/quiz/api/v1/" + createdQuiz.getId())).build();
    }
}
