package mangmae.harpseal.domain.quiz.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @NotNull
    @Pattern(
        regexp = "^(NONE|COUNT_ASC|COUNT_DESC|RECENT|OLD)",
        message = "검색 타입은 NONE, COUNT_ASC, COUNT_DESC, RECENT, OLD 중 하나여야 합니다."
    )
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
