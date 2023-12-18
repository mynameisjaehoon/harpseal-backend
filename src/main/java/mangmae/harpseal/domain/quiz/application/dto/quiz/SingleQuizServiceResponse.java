package mangmae.harpseal.domain.quiz.application.dto.quiz;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.quiz.repository.dto.SingleQuizRepositoryResponse;
import mangmae.harpseal.domain.quiz.application.dto.question.QuestionServiceDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleQuizServiceResponse {

    private Long quizId;
    private String title;
    private String description;
    private String thumbnailData;
    private List<QuestionServiceDto> questions = new ArrayList<>();

    public static SingleQuizServiceResponse fromRepositoryResponse(
            final SingleQuizRepositoryResponse dto,
            final String thumbnailData,
            final List<QuestionServiceDto> questions
    ) {
        return SingleQuizServiceResponse.builder()
                .quizId(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .thumbnailData(thumbnailData)
                .questions(questions)
                .build();
    }

}
