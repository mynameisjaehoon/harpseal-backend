package mangmae.harpseal.domain.question.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.choice.dto.ChoiceServiceDto;

import java.util.ArrayList;
import java.util.List;

/**
 * 문제(Question)에 대한 자세한 정보를 포함하는 DTO클래스이다.
 * 연관된 선택지(MultipleQuestionChoice)에 대한 정보도 포함하고 있다.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionServiceDto {

    private Long id;
    private String content;
    private int number;
    private String answer;
    private String attachmentData;
    private String attachmentType;
    private List<ChoiceServiceDto> choices;

    public static QuestionServiceDto fromSimpleServiceDto(QuestionSimpleServiceDto dto) {
        return QuestionServiceDto.builder()
            .id(dto.getId())
            .content(dto.getContent())
            .number(dto.getNumber())
            .answer(dto.getAnswer())
            .attachmentData(dto.getAttachmentData())
            .attachmentType(dto.getAttachmentType())
            .choices(new ArrayList<>())
            .build();
    }

    public void addChoice(ChoiceServiceDto choice) {
        choices.add(choice);
    }

    public void addChoices(List<ChoiceServiceDto> dtos) {
        choices.addAll(dtos);
    }
}
