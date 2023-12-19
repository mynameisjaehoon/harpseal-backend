package mangmae.harpseal.domain.quiz.application.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.question.dto.QuestionServiceDto;
import mangmae.harpseal.domain.question.dto.QuestionSimpleRepositoryDto;
import mangmae.harpseal.domain.quiz.repository.dto.SingleQuizRepositoryResponse;
import mangmae.harpseal.domain.question.dto.QuestionSimpleServiceDto;

import java.util.ArrayList;
import java.util.List;

/**
 * 복합적인 정보를 담고 있는 DTO
 * 퀴즈와 관련된 문제에 대한 정보를 모두 담고 있다.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleQuizServiceResponse {

    private Long quizId;
    private String title;
    private String description;
    private String thumbnailData;
    private List<QuestionServiceDto> questions;

//    public static SingleQuizServiceResponse fromRepositoryResponse(
//            final SingleQuizRepositoryResponse dto
//    ) {
//        return SingleQuizServiceResponse.builder()
//            .quizId(dto.getId())
//            .title(dto.getTitle())
//            .description(dto.getDescription())
//            .thumbnailData(null)
//            .questions(null)
//            .build();
//    }



    public void addThumbnailData(String data) {
        this.thumbnailData = data;
    }

    public void addQuestion(QuestionServiceDto dto) {
        questions.add(dto);
    }

}
