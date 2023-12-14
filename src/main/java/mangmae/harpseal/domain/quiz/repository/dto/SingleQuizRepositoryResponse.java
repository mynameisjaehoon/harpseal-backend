package mangmae.harpseal.domain.quiz.repository.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.question.dto.QuestionRepositoryDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SingleQuizRepositoryResponse {
    private String id;
    private String title;
    private String description;
    private String thumbnailFilePath;
    private List<QuestionRepositoryDto> questions = new ArrayList<>();

    public SingleQuizRepositoryResponse(
            final String id,
            final String title,
            final String description,
            final String thumbnailFilePath
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.thumbnailFilePath = thumbnailFilePath;
    }

    public void addQuestion(QuestionRepositoryDto dto) {
        questions.add(dto);
    }
}
