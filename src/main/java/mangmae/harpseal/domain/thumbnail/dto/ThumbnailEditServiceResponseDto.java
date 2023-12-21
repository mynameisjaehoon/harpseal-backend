package mangmae.harpseal.domain.thumbnail.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 썸네일이 수정된 정보를 담은 DTO
 */
@Getter
@AllArgsConstructor
public class ThumbnailEditServiceResponseDto {

    private String editedPath;

}
