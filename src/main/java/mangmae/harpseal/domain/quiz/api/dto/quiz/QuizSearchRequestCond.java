package mangmae.harpseal.domain.quiz.api.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.quiz.dto.QuizSearchType;
import mangmae.harpseal.domain.quiz.application.dto.quiz.QuizSearchServiceCond;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizSearchRequestCond {
    private String title;
    private String searchType;

    public QuizSearchServiceCond toServiceDto() {
        QuizSearchType type = QuizSearchType.by(searchType);

        return QuizSearchServiceCond.builder()
            .title(title)
            .searchType(type)
            .build();
    }
}
