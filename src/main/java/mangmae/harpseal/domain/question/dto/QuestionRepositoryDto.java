package mangmae.harpseal.domain.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.choice.dto.ChoiceRepositoryDto;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRepositoryDto {

    private String id;
    private String content;
    private int number;
    private String answer;
    private String type;
    private String attachmentPath;
    private List<ChoiceRepositoryDto> choices = new ArrayList<>();

    public QuestionRepositoryDto(
            String id,
            String content,
            int number,
            String answer
    ) {
        this.id = id;
        this.content = content;
        this.number = number;
        this.answer = answer;
    }

    public void addChoice(ChoiceRepositoryDto dto) {
        choices.add(dto);
    }
}
