package mangmae.harpseal.domain.quiz.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import mangmae.harpseal.entity.QuizThumbnail;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static mangmae.harpseal.entity.QQuizThumbnail.*;

@Repository
public class ThumbnailRepositoryImpl implements ThumbnailQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ThumbnailRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Long deleteByQuizId(Long quizId) {
        return queryFactory
            .delete(quizThumbnail)
            .where(quizThumbnail.quiz.id.eq(quizId))
            .execute();
    }

    public Optional<QuizThumbnail> findByQuizId(Long quizId) {
        QuizThumbnail thumbnail = queryFactory
            .select(quizThumbnail)
            .from(quizThumbnail)
            .where(quizThumbnail.quiz.id.eq(quizId))
            .fetchOne();
        return Optional.ofNullable(thumbnail);
    }


}
