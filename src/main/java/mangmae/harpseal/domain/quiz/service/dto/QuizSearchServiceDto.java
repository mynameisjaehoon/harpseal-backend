package mangmae.harpseal.domain.quiz.service.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizSearchServiceDto {
    private String id;
    private String title;
    private String description;
    private byte[] imageData;
}
