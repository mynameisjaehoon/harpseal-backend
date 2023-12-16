package mangmae.harpseal.domain.thumbnail;

import mangmae.harpseal.entity.QuizThumbnail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ThumbnailRepository extends JpaRepository<QuizThumbnail, Long>, ThumbnailQueryRepository {
}
