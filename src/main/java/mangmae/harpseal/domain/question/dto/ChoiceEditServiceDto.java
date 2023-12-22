package mangmae.harpseal.domain.question.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.choice.ChoiceEditRepositoryDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceEditServiceDto {

    private int number;
    private String content;

    public ChoiceEditRepositoryDto toRepositoryDto() {
        return ChoiceEditRepositoryDto.builder()
            .number(number)
            .content(content)
            .build();
    }

}
