package mangmae.harpseal.domain.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizSearchCond {
    private String title;
    private QuizSearchType searchType;

    public QuizSearchCond(String title, String searchType) {
        this.title = title;
        this.searchType = QuizSearchType.by(searchType);
    }
}
