package mangmae.harpseal.domain.quiz.repository.dto.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceEditRepositoryDto {

    private Long questionId;
    private int number;
    private String content;

}
