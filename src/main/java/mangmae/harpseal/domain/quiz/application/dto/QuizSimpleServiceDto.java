package mangmae.harpseal.domain.quiz.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 퀴즈의 단순한 정보만을 담고 있는 DTO
 * 관련된 문제와 관련된 정보는 담고 있지 않다.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizSimpleServiceDto {

    private Long id;
    private String title;
    private String description;
    private String thumbnailData;

    public static QuizSimpleServiceDto fromRepositoryDto(QuizSimpleRepositoryDto dto) {
        return QuizSimpleServiceDto.builder()
            .id(dto.getId())
            .title(dto.getTitle())
            .description(dto.getDescription())
            .thumbnailData(null)
            .build();
    }

    public void addThumbnailData(String data) {
        thumbnailData = data;
    }
}
