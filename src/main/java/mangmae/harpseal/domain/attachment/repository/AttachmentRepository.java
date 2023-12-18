package mangmae.harpseal.domain.attachment.repository;

import mangmae.harpseal.global.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long>, AttachmentQueryRepository {
}
