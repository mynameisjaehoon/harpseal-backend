package mangmae.harpseal.domain.question.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.global.entity.type.QuestionType;

/**
 * 문제(Question) 정보가 담긴 DTO 클래스
 * 관련된 선택지(MultipleQustionChoice)에 대한 정보는 가지고 있지 않다.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionSimpleServiceDto {

    private Long id;
    private String content;
    private int number;
    private String answer;
    private String attachmentData = null;
    private String attachmentType = "NONE";

    public static QuestionSimpleServiceDto fromRepositoryDto(
            final QuestionSimpleRepositoryDto dto
    ) {
        return QuestionSimpleServiceDto.builder()
            .id(dto.getId())
            .content(dto.getContent())
            .number(dto.getNumber())
            .answer(dto.getAnswer())
            .attachmentType(QuestionType.getNameOf(dto.getType()))
            .attachmentData(null)
            .build();
    }

    public void addAttachmentData(String data) {
        attachmentData = data;
    }
}
