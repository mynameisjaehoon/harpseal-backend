package mangmae.harpseal.domain.comment.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCommentRequestForm {

    private Long commentId;
    private String password;

    public DeleteCommentServiceForm toServiceForm(Long quizId) {
        return new DeleteCommentServiceForm(quizId, commentId, password);
    }
}
