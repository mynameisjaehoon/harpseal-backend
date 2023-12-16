package mangmae.harpseal.domain.quiz.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.quiz.service.dto.QuizEditServiceDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizEditRequestDto {

    private String title;
    private String description;
    private String password;

    public QuizEditServiceDto toServiceDto(Long quizId) {
        return new QuizEditServiceDto(quizId, title, description, password);
    }
}
