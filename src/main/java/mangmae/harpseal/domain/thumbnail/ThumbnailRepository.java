package mangmae.harpseal.domain.thumbnail.repository;

import mangmae.harpseal.entity.QuizThumbnail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThumbnailRepository extends JpaRepository<QuizThumbnail, Long> {
}
