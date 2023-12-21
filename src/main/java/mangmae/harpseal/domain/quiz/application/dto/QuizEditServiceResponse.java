package mangmae.harpseal.domain.quiz.application.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.global.entity.Quiz;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizEditServiceResponse {

    private Long id;
    private String title;
    private String description;
    private int likeCount;
    private int playTime;
    private String thumbnailImage;
    private String message;

    public static QuizEditServiceResponse fromQuizEntity(
        final Quiz quiz,
        final String imageBase64EncodingData,
        final String message
    ) {
        return QuizEditServiceResponse.builder()
            .id(quiz.getId())
            .title(quiz.getTitle())
            .description(quiz.getDescription())
            .likeCount(quiz.getLikeCount())
            .playTime(quiz.getPlayTime())
            .thumbnailImage(imageBase64EncodingData)
            .message(message)
            .build();
    }

    public static QuizEditServiceResponse fromQuizEntity(final Quiz quiz) {
        return fromQuizEntity(quiz, null, null);
    }

}
