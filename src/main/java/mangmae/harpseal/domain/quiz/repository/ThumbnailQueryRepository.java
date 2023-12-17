package mangmae.harpseal.domain.quiz.repository;

import mangmae.harpseal.entity.QuizThumbnail;

import java.util.Optional;

public interface ThumbnailQueryRepository {

    public Long deleteByQuizId(Long quizId);

    public Optional<QuizThumbnail> findByQuizId(Long quizId);

}
