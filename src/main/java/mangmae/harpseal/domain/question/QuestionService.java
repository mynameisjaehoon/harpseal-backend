package mangmae.harpseal.domain.question;


import lombok.RequiredArgsConstructor;
import mangmae.harpseal.entity.Question;
import mangmae.harpseal.entity.Quiz;
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
