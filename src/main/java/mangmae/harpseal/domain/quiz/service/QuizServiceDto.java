package mangmae.harpseal.domain.quiz.service;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class QuizServiceDto {
    private String title;
    private String password;
    private String description;
}
