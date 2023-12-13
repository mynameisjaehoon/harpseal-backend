package mangmae.harpseal.domain.quiz.repository;


import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import mangmae.harpseal.domain.quiz.repository.dto.QuizSearchRepositoryCond;
import mangmae.harpseal.domain.quiz.repository.dto.QuizSearchRepositoryDto;
import mangmae.harpseal.entity.QQuiz;
import mangmae.harpseal.entity.QQuizThumbnail;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static mangmae.harpseal.entity.QQuiz.*;
import static mangmae.harpseal.entity.QQuizThumbnail.*;


@Repository
public class QuizRepositoryImpl implements QuizQueryRepository {

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
    public List<QuizSearchRepositoryDto> findPlayTimeDesc(
        final QuizSearchRepositoryCond condition,
        final long offset,
        final int size
    ) {
        return queryFactory
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
            .offset(offset)
            .limit(size)
            .fetch();
    }

    /**
     * 플레이 횟수가 적은 순으로 퀴즈 정보를 담아주는 메서드
     * @param condition 검색 조건
     * @return
     */
    @Override
    public List<QuizSearchRepositoryDto> findPlayTimeAsc(
        final QuizSearchRepositoryCond condition,
        final long offset,
        final int size
    ) {

        return queryFactory
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
            .offset(offset)
            .limit(size)
            .fetch();
    }

    /**
     * 최근에 만들어진 순으로 퀴즈 정보를 담아주는 메서드
     * @param condition 검색 조건
     * @return
     */
    @Override
    public List<QuizSearchRepositoryDto> findRecentDesc(
        final QuizSearchRepositoryCond condition,
        final long offset,
        final int size
    ) {
        // 최근에 만들어진 순서는 만들어진 날짜를 기준으로 내림차순으로 정렬해서 결과를 반환하면 된다.

        return queryFactory
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
            .offset(offset)
            .limit(size)
            .fetch();

    }

    /**
     * 오래된 순으로 퀴즈 정보를 담아주는 메서드
     * @param condition 검색 조건
     * @return
     */
    @Override
    public List<QuizSearchRepositoryDto> findRecentAsc(
        final QuizSearchRepositoryCond condition,
        final long offset,
        final int size
    ) {
        // 만들어진 날짜 오름차순으로 데이터를 가져오면 오래된 순으로 퀴즈 정보를 가져올 수 있다.
        return queryFactory
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
            .offset(offset)
            .limit(size)
            .fetch();
    }

    private BooleanExpression quizTitleContains(String quizTitle) {
        return StringUtils.hasText(quizTitle) ? quiz.title.contains(quizTitle) : null;
    }
}
