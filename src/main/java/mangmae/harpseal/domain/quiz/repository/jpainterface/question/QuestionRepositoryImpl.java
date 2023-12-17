package mangmae.harpseal.domain.quiz.repository.jpainterface.question;

import mangmae.harpseal.entity.Question;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionRepositoryImpl implements QuestionQueryRepository {
    @Override
    public Question findQuestion(Long quizId, int number) {
        return null;
    }
}
