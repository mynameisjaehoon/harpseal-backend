package mangmae.harpseal.domain.quiz.service.dto.question;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.quiz.repository.dto.question.ChoiceEditRepositoryDto;

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
