package mangmae.harpseal.domain.attachment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mangmae.harpseal.entity.type.AttachmentType;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentServiceDto {

    private AttachmentType type;
    private String filePath;

}
