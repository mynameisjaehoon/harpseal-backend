package mangmae.harpseal.domain.quiz.service.dto.question;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.choice.dto.ChoiceServiceDto;
import mangmae.harpseal.domain.quiz.repository.dto.QuestionRepositoryDto;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionServiceDto {

    private String content;
    private int number;
    private String answer;
    private String type;
    private String attachmentData;
    List<ChoiceServiceDto> choices;

    public static QuestionServiceDto fromRepositoryDto(
            final QuestionRepositoryDto dto,
            final String data,
            final List<ChoiceServiceDto> choices) {

        return QuestionServiceDto.builder()
                .content(dto.getContent())
                .number(dto.getNumber())
                .answer(dto.getAnswer())
                .type(dto.getType().toString())
                .attachmentData(data)
                .choices(choices)
                .build();

    }

}
