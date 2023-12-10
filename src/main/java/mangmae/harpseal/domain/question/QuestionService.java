package mangmae.harpseal.domain.question;


import lombok.RequiredArgsConstructor;
import mangmae.harpseal.entity.Question;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Question save(Question question) {
        return questionRepository.save(question);
    }
}
