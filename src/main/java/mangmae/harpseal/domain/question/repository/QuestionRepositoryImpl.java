package mangmae.harpseal.domain.question.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import mangmae.harpseal.domain.question.exception.CannotFindQuestionException;
import mangmae.harpseal.domain.choice.ChoiceEditRepositoryDto;
import mangmae.harpseal.domain.question.dto.QuestionEditRepositoryDto;
import mangmae.harpseal.global.entity.MultipleQuestionChoice;
import mangmae.harpseal.global.entity.Question;
import org.springframework.stereotype.Repository;

import static mangmae.harpseal.entity.QMultipleQuestionChoice.*;
import static mangmae.harpseal.entity.QQuestion.*;

@Repository
public class QuestionRepositoryImpl implements QuestionQueryRepository {

    private final JPAQueryFactory queryFactory;

    public QuestionRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Question findQuestion(Long quizId, int number) {
        Question result = queryFactory
            .selectFrom(question)
            .where(question.quiz.id.eq(quizId), question.number.eq(number))
            .fetchOne();

        if (result == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("can not find quiz id=[").append(quizId).append("], ");
            sb.append("question number=[").append(number).append("] question");
            throw new CannotFindQuestionException(sb.toString());
        }

        return result;
    }

    @Override
    public Long updateQuestion(QuestionEditRepositoryDto dto) {
        Long quizId = dto.getQuizId();
        int number = dto.getNumber();
        return queryFactory
            .update(question)
            .set(question.content, dto.getContent())
            .set(question.answer, dto.getAnswer())
            .set(question.questionType, dto.getType())
            .where(
                question.quiz.id.eq(quizId),
                question.number.eq(number)
            )
            .execute();
    }


    @Override
    public Long deleteChoices(Long questionId) {
        return queryFactory
            .delete(multipleQuestionChoice)
            .where(multipleQuestionChoice.question.id.eq(questionId))
            .execute();
    }

    public void insertChoice(
        final Question existQuestion,
        final ChoiceEditRepositoryDto dto
    ) {
        MultipleQuestionChoice newChoice = new MultipleQuestionChoice(dto.getNumber(), dto.getContent());
        existQuestion.addChoice(newChoice);
    }

}
