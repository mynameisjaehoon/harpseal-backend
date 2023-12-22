package mangmae.harpseal.domain.question.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.global.entity.type.QuestionType;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionSimpleRepositoryDto {

    private Long id;
    private String content;
    private Integer number;
    private String answer;
    private QuestionType type;
    private String attachmentPath;

}
