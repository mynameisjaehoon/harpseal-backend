package mangmae.harpseal.domain.quiz.repository;

import mangmae.harpseal.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
