package mangmae.harpseal.domain.choice.dto;

public class ChoiceCreateDto {
    private String content;

    public ChoiceServiceDto toServiceDto() {
        return ChoiceServiceDto.builder()
            .content(content)
            .build();
    }
}
