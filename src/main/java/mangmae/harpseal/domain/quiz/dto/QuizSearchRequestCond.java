package mangmae.harpseal.domain.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.quiz.dto.QuizSearchType;
import mangmae.harpseal.domain.quiz.application.dto.QuizSearchServiceCond;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizSearchRequestCond {
    private String title;
    private String searchType;

    public QuizSearchRequestCond(String searchType) {
        this.searchType = searchType;
    }

    public QuizSearchServiceCond toServiceDto() {
        QuizSearchType type = QuizSearchType.by(searchType);

        return QuizSearchServiceCond.builder()
            .title(title)
            .searchType(type)
            .build();
    }
}
