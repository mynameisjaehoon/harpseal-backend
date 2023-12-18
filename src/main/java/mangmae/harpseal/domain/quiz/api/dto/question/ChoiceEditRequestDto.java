package mangmae.harpseal.domain.quiz.api.dto.question;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceEditRequestDto {
    private int number;
    private String content;
}
