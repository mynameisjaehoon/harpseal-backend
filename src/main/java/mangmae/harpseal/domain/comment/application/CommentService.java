package mangmae.harpseal.domain.comment.application;


import lombok.RequiredArgsConstructor;
import mangmae.harpseal.domain.comment.dto.CreateCommentRequestServiceForm;
import mangmae.harpseal.domain.comment.dto.CreateCommentResponseDto;
import mangmae.harpseal.domain.comment.dto.DeleteCommentRepositoryDto;
import mangmae.harpseal.domain.comment.dto.DeleteCommentServiceForm;
import mangmae.harpseal.domain.comment.exception.CannotFindCommentException;
import mangmae.harpseal.domain.comment.repository.CommentRepository;
import mangmae.harpseal.global.entity.Comment;
import mangmae.harpseal.global.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static mangmae.harpseal.global.util.SecurityUtil.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment findById(Long id) {
        Optional<Comment> findComment = commentRepository.findById(id);
        return findComment.orElseThrow(() -> new CannotFindCommentException(id));
    }

    public Comment createComment(final CreateCommentRequestServiceForm form) {
        String commentPassword = form.getPassword();
        String content = form.getContent();

        Comment newComment = new Comment(content, commentPassword);
        Comment savedComment = commentRepository.save(newComment);

        return savedComment;
    }

    public void deleteComment(final DeleteCommentServiceForm form) {
        // verify password
        String requestPassword = form.getPassword();
        Long commentId = form.getCommentId();

        Comment findComment = findById(commentId);
        verifyPassword(findComment.getPassword(), requestPassword);

        commentRepository.deleteById(commentId);
    }

}
