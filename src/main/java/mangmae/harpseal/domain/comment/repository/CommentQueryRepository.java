package mangmae.harpseal.domain.comment.repository;

import mangmae.harpseal.domain.comment.dto.DeleteCommentRepositoryDto;
import mangmae.harpseal.global.entity.Comment;

public interface CommentQueryRepository {

    public DeleteCommentRepositoryDto findByIdWithQuiz(Long id);

}
