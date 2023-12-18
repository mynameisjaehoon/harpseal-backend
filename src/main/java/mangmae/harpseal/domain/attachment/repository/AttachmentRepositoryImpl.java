package mangmae.harpseal.domain.attachment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import mangmae.harpseal.domain.attachment.repository.AttachmentQueryRepository;
import mangmae.harpseal.global.entity.Attachment;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static mangmae.harpseal.entity.QAttachment.*;


@Repository
public class AttachmentRepositoryImpl implements AttachmentQueryRepository {

    private final JPAQueryFactory queryFactory;

    public AttachmentRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Attachment> findAttachment(Long questionId) {
        Attachment result = queryFactory
            .selectFrom(attachment)
            .where(attachment.question.id.eq(questionId))
            .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public Long deleteAttachment(Long questionId) {
        return queryFactory
            .delete(attachment)
            .where(attachment.question.id.eq(questionId))
            .execute();
    }
}
