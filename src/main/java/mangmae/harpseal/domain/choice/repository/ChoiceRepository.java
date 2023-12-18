package mangmae.harpseal.domain.choice.repository;

import mangmae.harpseal.global.entity.MultipleQuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoiceRepository extends JpaRepository<MultipleQuestionChoice, Long> {
}
