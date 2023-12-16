package mangmae.harpseal.domain.quiz.service.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizEditServiceResponse {

    private Long id;
    private String title;
    private String description;
    private String thumbnailImage;
    private String message;

}
