package mangmae.harpseal.domain.quiz.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.quiz.dto.QuizSearchType;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizSearchServiceCond {
    private String title;
    private QuizSearchType searchType;
}
