package mangmae.harpseal.domain.quiz.repository.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizEditRepositoryDto {

    private Long id;
    private String title;
    private String description;

}
