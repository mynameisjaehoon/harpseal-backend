package mangmae.harpseal.domain.dto;


import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@Builder
public class QuizRegistrationFormDto {

    private final String title; //퀴즈 이름
    private final String password; //비밀번호
    private final String description;
    private final String thumbnailData; // 썸네일 이미지 데이터

}
