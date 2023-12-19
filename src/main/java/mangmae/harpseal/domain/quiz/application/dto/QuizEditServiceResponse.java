package mangmae.harpseal.domain.quiz.application.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizEditServiceResponse {

    private Long id;
    private String title;
    private String description;
    private String thumbnailImage;
    private String message;

}
