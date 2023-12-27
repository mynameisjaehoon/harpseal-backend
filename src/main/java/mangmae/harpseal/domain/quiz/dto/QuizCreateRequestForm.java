package mangmae.harpseal.domain.quiz.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;
import mangmae.harpseal.domain.quiz.application.dto.QuizCreateServiceDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizCreateRequestForm {

    private String title; //퀴즈 이름
    private String password; //비밀번호
    private String description;

    public QuizCreateServiceDto toServiceDto() {
        return QuizCreateServiceDto.builder()
            .title(title)
            .password(password)
            .description(description)
            .build();
    }

}
