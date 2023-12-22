package mangmae.harpseal.domain.quiz.api.dto.question;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.question.dto.ChoiceEditServiceDto;
import mangmae.harpseal.domain.question.dto.QuestionEditServiceDto;
import mangmae.harpseal.global.entity.type.QuestionType;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEditRequestDto {

    private String password;
    private String content;
    private String answer;
    private String type;
    private List<ChoiceEditRequestDto> choices;

    public QuestionEditServiceDto toServiceDto(Long quizId, int number) {
        List<ChoiceEditServiceDto> serviceChoices
            = choices.stream()
            .map((ch) -> {
                return new ChoiceEditServiceDto(ch.getNumber(), ch.getContent());
            })
            .toList();

        return QuestionEditServiceDto.builder()
            .quizId(quizId)
            .content(content)
            .answer(answer)
            .password(password)
            .number(number)
            .type(QuestionType.by(type))
            .choices(serviceChoices)
            .build();
    }
}
