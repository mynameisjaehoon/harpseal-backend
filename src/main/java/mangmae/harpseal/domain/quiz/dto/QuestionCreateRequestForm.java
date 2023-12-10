package mangmae.harpseal.domain.quiz.dto;

import lombok.*;
import mangmae.harpseal.domain.question.QuestionServiceDto;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class QuestionCreateRequestForm {

    private String content;
    private String password;
    private int number;
    private String type;
    private String answer;
    private String attachmentType;
    private List<ChoiceCreateDto> choices;

    public QuestionCreateRequestForm(String content, int number, String type, String answer) {
        this.content = content;
        this.number = number;
        this.type = type;
        this.answer = answer;
    }

    public QuestionServiceDto toServiceDto() {
        return QuestionServiceDto.builder()
            .content(content)
            .password(password)
            .number(number)
            .type(type)
            .answer(answer)
            .attachmentType(attachmentType)
            .choices(choices)
            .build();
    }


}
