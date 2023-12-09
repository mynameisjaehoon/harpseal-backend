package mangmae.harpseal.domain.quiz.controller;

import lombok.RequiredArgsConstructor;
import mangmae.harpseal.domain.quiz.dto.QuizListDto;
import mangmae.harpseal.domain.quiz.service.QuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/quiz/api/v1")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    /**
     * 퀴즈 리스트를 반환하는 컨트롤러 메서드
     * @return 퀴즈 리스트 DTO
     */
    @GetMapping("/list")
    public QuizListDto quizList() {
        return null;
    }
}