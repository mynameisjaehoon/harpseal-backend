package mangmae.harpseal.domain.quiz.repository.jpainterface.attachment;

import mangmae.harpseal.entity.Attachment;
import mangmae.harpseal.entity.MultipleQuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long>, AttachmentQueryRepository {
}
