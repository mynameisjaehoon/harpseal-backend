package mangmae.harpseal.domain.quiz.service.dto.question;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.choice.dto.ChoiceServiceDto;
import mangmae.harpseal.domain.quiz.repository.dto.question.ChoiceEditRepositoryDto;
import mangmae.harpseal.domain.quiz.repository.dto.question.QuestionEditRepositoryDto;
import mangmae.harpseal.entity.type.QuestionType;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEditServiceDto {

    private Long quizId;
    private String password;
    private String content;
    private String answer;
    private int number;
    private QuestionType type;
    private List<ChoiceEditServiceDto> choices;

    public QuestionEditRepositoryDto toRepositoryDto() {


        return QuestionEditRepositoryDto.builder()
            .quizId(quizId)
            .content(content)
            .answer(answer)
            .number(number)
            .type(type)
            .build();
    }
}
