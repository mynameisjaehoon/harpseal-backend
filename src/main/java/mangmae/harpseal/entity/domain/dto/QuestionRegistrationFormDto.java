package mangmae.harpseal.entity.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuestionRegistrationFormDto {

    private final String content;
    private final String type;
    private final String answer;
    private final String attachmentData;

}
