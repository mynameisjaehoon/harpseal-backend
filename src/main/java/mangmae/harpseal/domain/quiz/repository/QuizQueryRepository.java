package mangmae.harpseal.domain.quiz.repository;

import mangmae.harpseal.domain.quiz.repository.dto.QuizSearchRepositoryCond;
import mangmae.harpseal.domain.quiz.repository.dto.QuizSearchRepositoryDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuizQueryRepository {

    public List<QuizSearchRepositoryDto> findPlayTimeDesc(QuizSearchRepositoryCond condition, long offset, int limit);

    public List<QuizSearchRepositoryDto> findPlayTimeAsc(QuizSearchRepositoryCond condition, long offset, int limit);

    public List<QuizSearchRepositoryDto> findRecentDesc(QuizSearchRepositoryCond condition, long offset, int limit);

    public List<QuizSearchRepositoryDto> findRecentAsc(QuizSearchRepositoryCond condition, long offset, int limit);

}
