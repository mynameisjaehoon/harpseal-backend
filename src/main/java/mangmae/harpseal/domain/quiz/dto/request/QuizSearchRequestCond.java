package mangmae.harpseal.domain.quiz.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.quiz.dto.QuizSearchType;
import mangmae.harpseal.domain.quiz.service.dto.QuizSearchServiceCondition;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizSearchRequestCond {
    private String title;
    private QuizSearchType searchType;

    public QuizSearchRequestCond(String title, String searchType) {
        this.title = title;
        this.searchType = QuizSearchType.by(searchType);
    }

    public QuizSearchServiceCondition toServiceDto() {
        return QuizSearchServiceCondition.builder()
            .title(title)
            .searchType(searchType)
            .build();
    }
}
