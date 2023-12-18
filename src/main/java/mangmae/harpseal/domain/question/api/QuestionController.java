package mangmae.harpseal.domain.question.api;


import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mangmae.harpseal.domain.quiz.api.dto.question.QuestionEditRequestDto;
import mangmae.harpseal.domain.quiz.application.QuizService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/v1/quiz")
@RequiredArgsConstructor
public class QuestionController {

    // TODO: 2023/12/19 FACADE 에 의존하도록 수정
    private final QuizService quizService;

    @PutMapping(value = "/{quizId}/{number}", consumes = {APPLICATION_JSON_VALUE, MULTIPART_FORM_DATA_VALUE})
    public void editQuestion(
        @PathVariable("quizId") Long quizId,
        @PathVariable("number") int number,
        @RequestPart(value = "form") QuestionEditRequestDto dto,
        @RequestPart(value = "attachment", required = false) MultipartFile attachment,
        HttpServletResponse response
    ) throws IOException {

        quizService.editQuestion(dto.toServiceDto(quizId, number), attachment);

        StringBuilder urlBuilder = new StringBuilder("http://localhost:8080/api/v1/quiz/");
        urlBuilder.append(quizId);
        response.sendRedirect(urlBuilder.toString());
    }

}
