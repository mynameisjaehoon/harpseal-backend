package mangmae.harpseal.domain.choice.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceServiceDto {

    private int number;
    private String content;

    public static ChoiceServiceDto fromRepositoryDto(ChoiceRepositoryDto dto) {
        return new ChoiceServiceDto(dto.getNumber(), dto.getContent());
    }
}
