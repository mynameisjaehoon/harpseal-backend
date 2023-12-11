package mangmae.harpseal.domain.quiz.repository;

import mangmae.harpseal.domain.quiz.dto.request.QuizSearchRequestCond;
import mangmae.harpseal.domain.quiz.dto.response.QuizSearchResponseDto;

import java.util.List;

public interface QuizQueryRepository {

    public List<QuizSearchResponseDto> find(QuizSearchRequestCond condition);

}
