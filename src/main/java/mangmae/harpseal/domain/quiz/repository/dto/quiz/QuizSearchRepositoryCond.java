package mangmae.harpseal.domain.quiz.repository.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizSearchRepositoryCond {
    private String title;
}
