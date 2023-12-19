package mangmae.harpseal.domain.choice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceRepositoryDto {
    private Long questionId;
    private int number;
    private String content;
}
