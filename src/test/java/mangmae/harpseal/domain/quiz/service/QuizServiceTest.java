package mangmae.harpseal.domain.quiz.service;

import jakarta.persistence.EntityManager;
import mangmae.harpseal.domain.dto.QuestionRegistrationFormDto;
import mangmae.harpseal.domain.dto.QuizRegistrationFormDto;
import mangmae.harpseal.entity.Quiz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class QuizServiceTest {

    @Autowired
    QuizService quizService;

    @Autowired
    EntityManager em;


    @Test
    void createQuizTest() {
        List<QuestionRegistrationFormDto> questions = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            QuestionRegistrationFormDto question = QuestionRegistrationFormDto.builder()
                    .content("예시문제" + i)
                    .number(i)
                    .type("MULTIPLE")
                    .answer("1")
                    .build();
            questions.add(question);
        }

        QuizRegistrationFormDto quizForm = QuizRegistrationFormDto.builder()
                .title("예시 퀴즈")
                .description("예시 문제 설명")
                .password("1q2w3e4r")
                .build();

        quizService.createQuiz(quizForm);

    }


}