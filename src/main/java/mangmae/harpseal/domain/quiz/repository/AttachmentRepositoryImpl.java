package mangmae.harpseal.domain.quiz.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import mangmae.harpseal.domain.quiz.repository.jpainterface.attachment.AttachmentQueryRepository;
import mangmae.harpseal.domain.quiz.repository.jpainterface.attachment.AttachmentRepository;
import mangmae.harpseal.entity.Attachment;
import mangmae.harpseal.entity.QAttachment;
import mangmae.harpseal.entity.Question;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
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
