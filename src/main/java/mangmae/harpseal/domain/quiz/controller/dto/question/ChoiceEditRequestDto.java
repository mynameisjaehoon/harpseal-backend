package mangmae.harpseal.domain.quiz.controller.dto.question;


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
