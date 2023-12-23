package mangmae.harpseal.domain.comment.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mangmae.harpseal.domain.application.QuizFacadeService;
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
    private final QuizFacadeService quizFacadeService;

    @PostMapping("/{quizId}/comment/new")
    public ResponseEntity<CreateCommentResponseDto> createComment(
        @PathVariable("quizId") Long quizId,
        @RequestBody CreateCommentRequestForm form
    ) {

        CreateCommentResponseDto result = quizFacadeService.createComment(form.toFacadeDto(quizId));
        return ResponseEntity
            .ok()
            .body(result);
    }

}
