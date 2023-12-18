package mangmae.harpseal.domain.thumbnail.repository;

import mangmae.harpseal.global.entity.QuizThumbnail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThumbnailRepository extends JpaRepository<QuizThumbnail, Long>, ThumbnailQueryRepository {
}
