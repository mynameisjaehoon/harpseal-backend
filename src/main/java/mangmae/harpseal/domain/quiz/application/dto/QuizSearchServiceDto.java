package mangmae.harpseal.domain.quiz.application.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.quiz.repository.dto.QuizSearchRepositoryDto;

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
