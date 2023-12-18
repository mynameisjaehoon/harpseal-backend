package mangmae.harpseal.domain.thumbnail.repository;

import mangmae.harpseal.global.entity.QuizThumbnail;

import java.util.Optional;

public interface ThumbnailQueryRepository {

    public Long deleteByQuizId(Long quizId);

    public Optional<QuizThumbnail> findByQuizId(Long quizId);

}
