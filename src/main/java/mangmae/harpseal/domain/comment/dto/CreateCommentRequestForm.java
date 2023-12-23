package mangmae.harpseal.domain.comment.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequestForm {

    private String content;
    private String password;

    public CreateCommentRequestServiceForm toServiceDto(CreateCommentRequestForm form) {
        return new CreateCommentRequestServiceForm(form.getContent(), form.getPassword());
    }

}
