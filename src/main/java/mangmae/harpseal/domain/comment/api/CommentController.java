package mangmae.harpseal.domain.comment.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mangmae.harpseal.domain.comment.application.CommentService;
import mangmae.harpseal.domain.comment.dto.CreateCommentRequestForm;
import mangmae.harpseal.domain.comment.dto.CreateCommentResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/v1/quiz/")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{quiz_id}/comment/new")
    public ResponseEntity<CreateCommentResponseDto> createComment(
        @RequestBody CreateCommentRequestForm form
    ) {
        CreateCommentResponseDto result = commentService.createComment(form.toServiceDto(form));
        return ResponseEntity
            .ok()
            .body(result);
    }

}
