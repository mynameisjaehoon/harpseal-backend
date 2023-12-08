package mangmae.harpseal.domain.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class QuizRegistrationFormDto {

    private final String name; //퀴즈 이름
    private final String password; //비밀번호
    private final Integer count; //문제 수
    private final List<QuestionRegistrationFormDto> questions;

}
