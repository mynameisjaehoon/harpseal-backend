package mangmae.harpseal.domain.quiz.repository;


import mangmae.harpseal.domain.quiz.dto.request.QuizSearchRequestCond;
import mangmae.harpseal.domain.quiz.dto.response.QuizSearchResponseDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizRepositoryImpl implements QuizQueryRepository{
    @Override
    public List<QuizSearchResponseDto> findWithCondition(QuizSearchRequestCond condition) {
        return null;
    }
}
