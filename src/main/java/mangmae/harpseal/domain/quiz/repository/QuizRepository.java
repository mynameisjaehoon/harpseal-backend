package mangmae.harpseal.domain.quiz.repository;

import mangmae.harpseal.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long>, QuizQueryRepository {
}
