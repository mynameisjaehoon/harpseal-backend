package mangmae.harpseal.domain.choice;

import mangmae.harpseal.entity.MultipleQuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoiceRepository extends JpaRepository<MultipleQuestionChoice, Long> {
}
