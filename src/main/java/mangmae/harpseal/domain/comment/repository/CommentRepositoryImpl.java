package mangmae.harpseal.domain.comment.repository;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import mangmae.harpseal.domain.comment.dto.DeleteCommentRepositoryDto;
import mangmae.harpseal.global.entity.QQuiz;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static mangmae.harpseal.global.entity.QComment.*;
import static mangmae.harpseal.global.entity.QQuiz.*;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public DeleteCommentRepositoryDto findByIdWithQuiz(Long id) {
        return queryFactory
            .select(
                Projections.constructor(
                    DeleteCommentRepositoryDto.class,
                    comment.id,
                    quiz.id
                )
            )
            .from(comment)
            .leftJoin(comment.quiz, quiz)
            .where(comment.id.eq(id))
            .fetchOne();
    }


}
