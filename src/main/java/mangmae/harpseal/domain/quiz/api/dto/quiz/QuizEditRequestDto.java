package mangmae.harpseal.domain.quiz.api.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.quiz.application.dto.QuizEditServiceRequestDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizEditRequestDto {

    private String title;
    private String description;
    private String password;

    public QuizEditServiceRequestDto toServiceDto(Long quizId) {
        return new QuizEditServiceRequestDto(quizId, title, description, password);
    }
}
