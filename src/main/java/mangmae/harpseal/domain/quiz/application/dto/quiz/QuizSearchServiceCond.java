package mangmae.harpseal.domain.quiz.application.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.quiz.dto.QuizSearchType;
import mangmae.harpseal.domain.quiz.repository.dto.QuizSearchRepositoryCond;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizSearchServiceCond {
    private String title;
    private QuizSearchType searchType;

    public QuizSearchRepositoryCond toRepositoryCond() {
        return QuizSearchRepositoryCond.builder()
            .title(title)
            .build();
    }
}
