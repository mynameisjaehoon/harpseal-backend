package mangmae.harpseal.domain.quiz.repository.jpainterface;

import mangmae.harpseal.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
