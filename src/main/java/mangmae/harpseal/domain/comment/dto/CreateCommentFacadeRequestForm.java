package mangmae.harpseal.domain.comment.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentFacadeRequestForm {

    private Long quizId;
    private String content;
    private String password;

    public CreateCommentRequestServiceForm toServiceDto() {
        return new CreateCommentRequestServiceForm(content, password);
    }

}
