package mangmae.harpseal.domain.comment.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {

    private String content;
    private String password;

    public CreateCommentFacadeServiceRequest toFacadeDto(Long quizId) {
        return new CreateCommentFacadeServiceRequest(quizId, content, password);
    }

}
