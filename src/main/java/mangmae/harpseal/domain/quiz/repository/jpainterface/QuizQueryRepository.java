package mangmae.harpseal.domain.quiz.repository.jpainterface;

import mangmae.harpseal.domain.quiz.repository.dto.question.QuestionEditRepositoryDto;
import mangmae.harpseal.domain.quiz.repository.dto.quiz.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuizQueryRepository {

    public Page<QuizSearchRepositoryDto> findPlayTimeDesc(QuizSearchRepositoryCond condition, Pageable pageable);

    public Page<QuizSearchRepositoryDto> findPlayTimeAsc(QuizSearchRepositoryCond condition, Pageable pageable);

    public Page<QuizSearchRepositoryDto> findRecentDesc(QuizSearchRepositoryCond condition, Pageable pageable);

    public Page<QuizSearchRepositoryDto> findRecentAsc(QuizSearchRepositoryCond condition, Pageable pageable);

    public SingleQuizRepositoryResponse findSingleQuizById(Long quizId);

    public QuizDeleteRepositoryResponse deleteQuizById(Long quizId);

    public String findPasswordById(Long quizId);

    public Long updateQuiz(QuizEditRepositoryDto dto);

    public void updateQuestion(QuestionEditRepositoryDto dto);

}
