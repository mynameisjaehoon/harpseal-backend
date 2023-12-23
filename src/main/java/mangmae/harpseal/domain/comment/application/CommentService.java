package mangmae.harpseal.domain.comment.application;


import lombok.RequiredArgsConstructor;
import mangmae.harpseal.domain.comment.dto.CreateCommentRequestServiceForm;
import mangmae.harpseal.domain.comment.dto.CreateCommentResponseDto;
import mangmae.harpseal.domain.comment.repository.CommentRepository;
import mangmae.harpseal.global.entity.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment createComment(final CreateCommentRequestServiceForm form) {
        String commentPassword = form.getPassword();
        String content = form.getContent();

        Comment newComment = new Comment(content, commentPassword);
        Comment savedComment = commentRepository.save(newComment);

        return savedComment;
    }

}
