package mangmae.harpseal.domain.comment.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentFacadeServiceRequest {

    private Long quizId;
    private String content;
    private String password;

    public CreateCommentServiceRequest toServiceForm() {
        return new CreateCommentServiceRequest(content, password);
    }

}
