package mangmae.harpseal.domain.quiz.repository.dto.quiz;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.quiz.repository.dto.question.QuestionRepositoryDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleQuizRepositoryResponse {
    private Long id;
    private String title;
    private String description;
    private String thumbnailPath;
    private List<QuestionRepositoryDto> questions = new ArrayList<>();

    public void addQuestion(QuestionRepositoryDto dto) {
        questions.add(dto);
    }
}
