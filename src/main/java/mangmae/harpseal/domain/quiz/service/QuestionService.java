package mangmae.harpseal.domain.quiz.service;


import lombok.RequiredArgsConstructor;
import mangmae.harpseal.domain.quiz.repository.jpainterface.question.QuestionRepository;
import mangmae.harpseal.entity.Question;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Question save(Question question) {
        Question findQuestion = questionRepository.save(question);
        return findQuestion;
    }
}
