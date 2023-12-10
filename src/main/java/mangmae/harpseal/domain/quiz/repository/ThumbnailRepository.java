package mangmae.harpseal.domain.quiz.repository;

import mangmae.harpseal.entity.QuizThumbnail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThumbnailRepository extends JpaRepository<QuizThumbnail, Long> {
}
