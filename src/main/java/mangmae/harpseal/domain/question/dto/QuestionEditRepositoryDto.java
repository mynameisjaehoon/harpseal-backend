package mangmae.harpseal.domain.question.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.global.entity.type.QuestionType;

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
