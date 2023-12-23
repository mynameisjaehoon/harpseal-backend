package mangmae.harpseal.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentResponseDto {

    private String content;
    private String createdBy;
    private int like;
    private LocalDateTime createdDate;

}
