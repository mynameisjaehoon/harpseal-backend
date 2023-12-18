package mangmae.harpseal.domain.attachment.repository;


import mangmae.harpseal.global.entity.Attachment;

import java.util.Optional;

public interface AttachmentQueryRepository {

    public Optional<Attachment> findAttachment(Long questionId);

    public Long deleteAttachment(Long questionId);

}
