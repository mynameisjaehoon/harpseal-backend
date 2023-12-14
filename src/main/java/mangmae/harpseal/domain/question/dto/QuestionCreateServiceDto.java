package mangmae.harpseal.domain.question.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import mangmae.harpseal.domain.choice.dto.ChoiceServiceDto;

import java.util.List;

@Builder
@Getter
@ToString
public class QuestionCreateServiceDto {
    private String content;
    private String password;
    private int number;
    private String type;
    private String answer;
    private String attachmentType;
    private List<ChoiceServiceDto> choices;
}
