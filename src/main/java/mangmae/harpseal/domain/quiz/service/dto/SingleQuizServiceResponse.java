package mangmae.harpseal.domain.quiz.service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.question.dto.QuestionServiceDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleQuizServiceResponse {

    private String quizId;
    private String title;
    private String description;
    private String thumbnailData;
    private List<QuestionServiceDto> questions = new ArrayList<>();

}
