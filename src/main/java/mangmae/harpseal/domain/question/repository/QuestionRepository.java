package mangmae.harpseal.domain.question.repository;

import mangmae.harpseal.global.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionQueryRepository {
}
