package mangmae.harpseal.domain.quiz.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.choice.dto.ChoiceRepositoryDto;
import mangmae.harpseal.entity.type.QuestionType;

import java.util.ArrayList;
import java.util.List;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRepositoryDto {

    private Long id;
    private String content;
    private Integer number;
    private String answer;
    private QuestionType type;
    private String attachmentPath;
    private List<ChoiceRepositoryDto> choices = new ArrayList<>();

    public QuestionRepositoryDto(
            Long id,
            String content,
            Integer number,
            String answer,
            QuestionType type,
            String attachmentPath
    ) {
        this.id = id;
        this.content = content;
        this.number = number;
        this.answer = answer;
        this.type = type;
    }

    public void fetchChoices(List<ChoiceRepositoryDto> dto) {
        choices = dto;
    }
}
