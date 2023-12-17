package mangmae.harpseal.domain.quiz.repository.dto.question;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.choice.dto.ChoiceRepositoryDto;
import mangmae.harpseal.entity.type.QuestionType;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEditRepositoryDto {

    private Long quizId;
    private String content;
    private String answer;
    private int number;
    private QuestionType type;

}
