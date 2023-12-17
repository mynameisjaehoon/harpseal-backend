package mangmae.harpseal.domain.question.service;


import lombok.RequiredArgsConstructor;
import mangmae.harpseal.domain.question.repository.QuestionRepository;
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
