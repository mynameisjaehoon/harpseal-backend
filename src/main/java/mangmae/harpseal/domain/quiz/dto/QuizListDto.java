package mangmae.harpseal.domain.quiz.dto;

import lombok.Builder;

import java.util.List;

/**
 * 퀴즈 목록 정보를 담은 DTO
 */
@Builder
public class QuizListDto {

    private int count;
    private List<QuizDto> quizzes;

}
