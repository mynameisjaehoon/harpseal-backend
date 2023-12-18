package mangmae.harpseal.domain.quiz.api.dto.attachment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mangmae.harpseal.global.entity.type.AttachmentType;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentServiceDto {

    private AttachmentType type;
    private String filePath;

}
