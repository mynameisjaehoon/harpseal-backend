package mangmae.harpseal.domain.quiz.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizSimpleRepositoryDto {

    private Long id;
    private String title;
    private String description;
    private String thumbnailPath;

}
