package mangmae.harpseal.domain.quiz.dto;


import lombok.*;
import mangmae.harpseal.domain.quiz.service.QuizServiceDto;

@Data
public class QuizCreateRequestForm {

    private String title; //퀴즈 이름
    private String password; //비밀번호
    private String description;

    public QuizServiceDto toServiceDto() {
        return QuizServiceDto.builder()
            .title(title)
            .password(password)
            .description(description)
            .build();
    }

}
