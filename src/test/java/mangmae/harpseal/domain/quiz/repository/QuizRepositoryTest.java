package mangmae.harpseal.domain.quiz.repository;

import mangmae.harpseal.entity.Quiz;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class QuizRepositoryTest {

    @Autowired
    QuizRepository quizRepository;

    @Test
    void saveTest() {
        Quiz quiz = new Quiz("퀴즈 예제 1 입니다.", "1q2w3e4r");
        Quiz savedQuiz = quizRepository.save(quiz);
        Quiz findQuiz = quizRepository.findById(savedQuiz.getId()).orElseThrow(() -> new RuntimeException());

        assertThat(savedQuiz).isEqualTo(findQuiz);
    }

}