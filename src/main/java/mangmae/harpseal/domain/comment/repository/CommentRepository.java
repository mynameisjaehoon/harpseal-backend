package mangmae.harpseal.domain.comment.repository;

import mangmae.harpseal.global.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
