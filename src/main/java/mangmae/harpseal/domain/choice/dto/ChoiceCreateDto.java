package mangmae.harpseal.domain.choice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceCreateDto {

    private int number;
    private String content;

    public ChoiceServiceDto toServiceDto() {
        return new ChoiceServiceDto(number, content);
    }
}
