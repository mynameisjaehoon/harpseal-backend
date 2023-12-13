package mangmae.harpseal.domain.quiz.service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizSearchServiceDto {
    private Long id;
    private String title;
    private String description;
    private String imageData;
}
