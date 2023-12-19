package mangmae.harpseal.domain.choice.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import mangmae.harpseal.domain.choice.dto.ChoiceRepositoryDto;
import org.springframework.stereotype.Repository;

import java.util.List;

import static mangmae.harpseal.global.entity.QMultipleQuestionChoice.*;

@Repository
public class ChoiceRepositoryImpl implements ChoiceQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ChoiceRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ChoiceRepositoryDto> findQuestionChoices(Long questionId) {
        return queryFactory
            .select(
                Projections.constructor(
                    ChoiceRepositoryDto.class,
                    multipleQuestionChoice.question.id.as("questionId"),
                    multipleQuestionChoice.number,
                    multipleQuestionChoice.content
                )
            )
            .from(multipleQuestionChoice)
            .where(multipleQuestionChoice.question.id.eq(questionId))
            .orderBy(multipleQuestionChoice.number.asc())
            .fetch();

    }
}
