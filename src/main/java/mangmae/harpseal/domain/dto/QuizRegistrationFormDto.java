package mangmae.harpseal.domain.dto;


import lombok.*;

@Data
public class QuizRegistrationFormDto {

    private String title; //퀴즈 이름
    private String password; //비밀번호
    private String description;

}
