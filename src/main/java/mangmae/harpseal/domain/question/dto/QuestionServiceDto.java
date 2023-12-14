package mangmae.harpseal.domain.question.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.choice.dto.ChoiceServiceDto;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionServiceDto {

    private String content;
    private int number;
    private String answer;
    private String type;
    private String attachmentData;
    List<ChoiceServiceDto> choices;

}
