package mangmae.harpseal.domain.question;

import mangmae.harpseal.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
