package mangmae.harpseal.domain.quiz.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 퀴즈 하나의 정보를 담은 DTO
 */
@Getter
@Builder
public class QuizDto {

    private final Long id;
    private final String title;
    private final String description;
    private final String thumbnailImage;

}
