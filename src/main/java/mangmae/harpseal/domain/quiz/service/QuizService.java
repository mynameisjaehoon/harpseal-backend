package mangmae.harpseal.domain.quiz.service;


import lombok.RequiredArgsConstructor;
import mangmae.harpseal.domain.quiz.repository.QuizRepository;
import mangmae.harpseal.entity.Quiz;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;

    @Transactional
    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }
}
