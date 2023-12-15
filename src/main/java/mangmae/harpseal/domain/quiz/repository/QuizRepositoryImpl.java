package mangmae.harpseal.domain.quiz.repository;


import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import mangmae.harpseal.domain.choice.dto.ChoiceRepositoryDto;
import mangmae.harpseal.domain.exception.CannotFindQuizException;
import mangmae.harpseal.domain.question.dto.QuestionRepositoryDto;
import mangmae.harpseal.domain.quiz.repository.dto.QuizDeleteRepositoryResponse;
import mangmae.harpseal.domain.quiz.repository.dto.QuizSearchRepositoryCond;
import mangmae.harpseal.domain.quiz.repository.dto.QuizSearchRepositoryDto;
import mangmae.harpseal.domain.quiz.repository.dto.SingleQuizRepositoryResponse;
import mangmae.harpseal.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static mangmae.harpseal.entity.QAttachment.*;
import static mangmae.harpseal.entity.QMultipleQuestionChoice.*;
import static mangmae.harpseal.entity.QQuestion.*;
import static mangmae.harpseal.entity.QQuiz.*;
import static mangmae.harpseal.entity.QQuizThumbnail.*;


@Slf4j
@Repository
public class QuizRepositoryImpl implements QuizQueryRepository {

    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;

    public QuizRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 퀴즈를 검색하는 레포지토리 메서드이다.<br>
     * 검색조건 타입이 지정되어 있지 않다면 이 메서드로 검색된다.<br>
     * 플레이 횟수가 많은 순서대로 결과를 담아 반환한다.<br>
     * @param condition 검색 조건
     * @return 검색 데이터를 담은 DTO(QuizSearchRepositoryDto)
     */
    @Override
    public Page<QuizSearchRepositoryDto> findPlayTimeDesc(
        final QuizSearchRepositoryCond condition,
        Pageable pageable
    ) {
        List<QuizSearchRepositoryDto> result = queryFactory
            .select(
                Projections.constructor(
                    QuizSearchRepositoryDto.class,
                    quiz.id,
                    quiz.title,
                    quiz.description,
                    quizThumbnail.filePath
                )
            )
            .from(quiz)
            .leftJoin(quiz.thumbnail, quizThumbnail)
            .where(quizTitleContains(condition.getTitle()))
            .orderBy(quiz.playTime.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long total = queryFactory
            .select(quiz.count())
            .from(quiz)
            .where(quizTitleContains(condition.getTitle()))
            .fetchOne();

        return new PageImpl<>(result, pageable, total);
    }

    /**
     * 플레이 횟수가 적은 순으로 퀴즈 정보를 담아주는 메서드
     * @param condition 검색 조건
     * @return
     */
    @Override
    public Page<QuizSearchRepositoryDto> findPlayTimeAsc(
        final QuizSearchRepositoryCond condition,
        Pageable pageable
    ) {

        List<QuizSearchRepositoryDto> result = queryFactory
            .select(
                Projections.constructor(
                    QuizSearchRepositoryDto.class,
                    quiz.id,
                    quiz.title,
                    quiz.description,
                    quizThumbnail.filePath
                )
            )
            .from(quiz)
            .leftJoin(quiz.thumbnail, quizThumbnail)
            .where(quizTitleContains(condition.getTitle()))
            .orderBy(quiz.playTime.asc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long total = queryFactory
            .select(quiz.count())
            .from(quiz)
            .where(quizTitleContains(condition.getTitle()))
            .fetchOne();

        return new PageImpl<>(result, pageable, total);
    }

    /**
     * 최근에 만들어진 순으로 퀴즈 정보를 담아주는 메서드
     * @param condition 검색 조건
     * @return
     */
    @Override
    public Page<QuizSearchRepositoryDto> findRecentDesc(
        final QuizSearchRepositoryCond condition,
        Pageable pageable
    ) {
        // 최근에 만들어진 순서는 만들어진 날짜를 기준으로 내림차순으로 정렬해서 결과를 반환하면 된다.

        List<QuizSearchRepositoryDto> result = queryFactory
            .select(
                Projections.constructor(
                    QuizSearchRepositoryDto.class,
                    quiz.id,
                    quiz.title,
                    quiz.description,
                    quizThumbnail.filePath
                )
            )
            .from(quiz)
            .leftJoin(quiz.thumbnail, quizThumbnail)
            .where(quizTitleContains(condition.getTitle()))
            .orderBy(quiz.createdDate.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long total = queryFactory
            .select(quiz.count())
            .from(quiz)
            .where(quizTitleContains(condition.getTitle()))
            .fetchOne();

        return new PageImpl<>(result, pageable, total);
    }

    /**
     * 오래된 순으로 퀴즈 정보를 담아주는 메서드
     * @param condition 검색 조건
     * @return
     */
    @Override
    public Page<QuizSearchRepositoryDto> findRecentAsc(
        final QuizSearchRepositoryCond condition,
        Pageable pageable
    ) {
        // 만들어진 날짜 오름차순으로 데이터를 가져오면 오래된 순으로 퀴즈 정보를 가져올 수 있다.
        List<QuizSearchRepositoryDto> result = queryFactory
            .select(
                Projections.constructor(
                    QuizSearchRepositoryDto.class,
                    quiz.id,
                    quiz.title,
                    quiz.description,
                    quizThumbnail.filePath
                )
            )
            .from(quiz)
            .leftJoin(quiz.thumbnail, quizThumbnail)
            .where(quizTitleContains(condition.getTitle()))
            .orderBy(quiz.createdDate.asc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long total = queryFactory
            .select(quiz.count())
            .from(quiz)
            .where(quizTitleContains(condition.getTitle()))
            .fetchOne();

        return new PageImpl<>(result, pageable, total);
    }

    /**
     * 퀴즈 정보, 퀴즈에 담긴 문제, 문제의 첨부파일, 문제의 선지정보를 담아 반환한다.
     * @param quizId 반환 받고자 하는 퀴즈 ID
     * @return 퀴즈 정보를 담은 레포지토리 계층 DTO
     */
    @Override
    public SingleQuizRepositoryResponse findSingleQuizById(Long quizId) {

        long startTime = System.currentTimeMillis();

        Quiz findQuiz = em.find(Quiz.class, quizId);

        // TODO: 2023/12/15 Question, Thumbnail Image 데이터에 접근하는 쿼리들이 QuizRepository에 있는 것이 맞는가...
        List<QuestionRepositoryDto> questionDtoList = findQuestionWith(quizId);
        fetchChoiceData(questionDtoList);
        String thumbnailImagePath = findThumbnailPath(quizId);


        long time = System.currentTimeMillis() - startTime;
        log.info("time={}ms", time);

        return SingleQuizRepositoryResponse.builder()
                .id(quizId)
                .title(findQuiz.getTitle())
                .description(findQuiz.getDescription())
                .thumbnailPath(thumbnailImagePath)
                .questions(questionDtoList)
                .build();
    }

    @Override
    public QuizDeleteRepositoryResponse deleteQuizById(
        final Long quizId
    ) {
        long deletedId = queryFactory
            .delete(quiz)
            .where(quiz.id.eq(quizId))
            .execute();

        return new QuizDeleteRepositoryResponse(deletedId);
    }

    @Override
    public String findPasswordById(Long quizId) {
        return queryFactory
            .select(quiz.password)
            .from(quiz)
            .where(quizIdEq(quizId))
            .fetchFirst();
    }

    private String findThumbnailPath(Long quizId) {
        return queryFactory
                .select(quizThumbnail.filePath)
                .from(quizThumbnail)
                .where(quizThumbnail.quiz.id.eq(quizId))
                .fetchOne();
    }

    private void fetchChoiceData(List<QuestionRepositoryDto> questionDtoList) {
        questionDtoList
                .forEach(dto -> {
                    Long questionId = dto.getId();
                    List<ChoiceRepositoryDto> choiceResult = queryFactory
                            .select(
                                    Projections.constructor(
                                            ChoiceRepositoryDto.class,
                                            multipleQuestionChoice.content
                                    )
                            )
                            .from(multipleQuestionChoice)
                            .where(multipleQuestionChoice.question.id.eq(questionId))
                            .fetch();
                    dto.fetchChoices(choiceResult);
                });
    }

    private List<QuestionRepositoryDto> findQuestionWith(Long quizId) {
        return queryFactory
                .select(
                        Projections.constructor(
                                QuestionRepositoryDto.class,
                                question.id,
                                question.content,
                                question.number,
                                question.answer,
                                question.questionType,
                                attachment.filePath.as("attachmentPath")
                        )
                )
                .from(question)
                .leftJoin(question.attachment, attachment)
                .where(question.quiz.id.eq(quizId))
                .orderBy(question.number.asc())
                .fetch();
    }

    private BooleanExpression quizTitleContains(String quizTitle) {
        return StringUtils.hasText(quizTitle) ? quiz.title.contains(quizTitle) : null;
    }

    private BooleanExpression quizIdEq(Long id) {
        return quiz.id.eq(id);
    }
}
