package mangmae.harpseal.domain.question.repository;

import mangmae.harpseal.entity.Attachment;
import mangmae.harpseal.entity.MultipleQuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
