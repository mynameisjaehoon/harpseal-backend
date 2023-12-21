package mangmae.harpseal.domain.quiz.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.quiz.repository.dto.QuizEditRepositoryDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizEditServiceRequestDto {

    private Long id;
    private String title;
    private String description;
    private String password;

    public QuizEditRepositoryDto toRepositoryDto() {
        return new QuizEditRepositoryDto(id, title, description);
    }

}
