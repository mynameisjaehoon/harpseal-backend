package mangmae.harpseal.domain.quiz.repository.jpainterface.attachment;


import mangmae.harpseal.entity.Attachment;

import java.util.Optional;

public interface AttachmentQueryRepository {

    public Optional<Attachment> findAttachment(Long questionId);

    public Long deleteAttachment(Long questionId);

}
