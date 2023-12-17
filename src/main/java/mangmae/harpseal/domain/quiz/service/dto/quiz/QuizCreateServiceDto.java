package mangmae.harpseal.domain.quiz.service.dto.quiz;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class QuizCreateServiceDto {
    private String title;
    private String password;
    private String description;
}
