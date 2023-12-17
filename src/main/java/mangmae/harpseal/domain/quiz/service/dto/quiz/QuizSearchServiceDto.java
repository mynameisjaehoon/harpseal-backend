package mangmae.harpseal.domain.quiz.service.dto.quiz;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.quiz.repository.dto.quiz.QuizSearchRepositoryDto;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizSearchServiceDto {
    private Long id;
    private String title;
    private String description;
    private String imageData;

    public QuizSearchServiceDto(QuizSearchRepositoryDto quizSearchRepositoryDto) {

    }
}
