package mangmae.harpseal.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class QuestionRegistrationFormDto {

    private final String content;
    private final int number;
    private final String type;
    private final String answer;

}
