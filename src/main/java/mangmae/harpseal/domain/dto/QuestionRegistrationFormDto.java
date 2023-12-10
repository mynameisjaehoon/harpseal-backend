package mangmae.harpseal.domain.dto;

import lombok.*;

@Data
public class QuestionRegistrationFormDto {

    private String content;
    private int number;
    private String type;
    private String answer;

    public QuestionRegistrationFormDto(String content, int number, String type, String answer) {
        this.content = content;
        this.number = number;
        this.type = type;
        this.answer = answer;
    }
}
