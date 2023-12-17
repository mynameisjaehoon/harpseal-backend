package mangmae.harpseal.domain.quiz.service.dto.quiz;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.quiz.repository.dto.quiz.QuizEditRepositoryDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizEditServiceDto {

    private Long id;
    private String title;
    private String description;
    private String password;

    public QuizEditRepositoryDto toRepositoryDto() {
        return new QuizEditRepositoryDto(id, title, description);
    }

}
