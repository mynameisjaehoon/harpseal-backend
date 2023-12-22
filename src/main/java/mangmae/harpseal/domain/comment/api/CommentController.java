package mangmae.harpseal.domain.comment.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mangmae.harpseal.domain.comment.dto.CreateCommentRequestForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/v1/quiz/")
@RequiredArgsConstructor
public class CommentController {

    @PostMapping("/{quiz_id}/comment/new")
    public ResponseEntity<Void> createComment(
        @RequestBody CreateCommentRequestForm form
    ) {
        

        return null;
    }

}
