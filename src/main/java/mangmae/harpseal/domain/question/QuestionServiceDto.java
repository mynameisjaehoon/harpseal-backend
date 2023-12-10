package mangmae.harpseal.domain.question;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import mangmae.harpseal.domain.quiz.dto.ChoiceCreateDto;

import java.util.List;

@Builder
@Getter
@ToString
public class QuestionServiceDto {
    private String content;
    private String password;
    private int number;
    private String type;
    private String answer;
    private String attachmentType;
    private List<ChoiceCreateDto> choices;
}
