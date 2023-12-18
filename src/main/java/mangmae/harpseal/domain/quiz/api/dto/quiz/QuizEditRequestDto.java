package mangmae.harpseal.domain.quiz.api.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.quiz.application.dto.quiz.QuizEditServiceDto;

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
