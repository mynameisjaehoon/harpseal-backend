package mangmae.harpseal.domain.quiz.repository;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import mangmae.harpseal.domain.quiz.repository.dto.QuizSearchRepositoryCond;
import mangmae.harpseal.domain.quiz.repository.dto.QuizSearchRepositoryDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class QuizRepositoryImpl implements QuizQueryRepository {

    private final JPAQueryFactory queryFactory;

    /**
     * 퀴즈를 검색하는 레포지토리 메서드이다.<br>
     * 검색조건 타입이 지정되어 있지 않다면 이 메서드로 검색된다.<br>
     * 플레이 횟수가 많은 순서대로 결과를 담아 반환한다.<br>
     * @param condition 검색 조건
     * @param pageable 페이징 검색 조건
     * @return 검색 데이터를 담은 DTO(QuizSearchRepositoryDto)
     */
    @Override
    public List<QuizSearchRepositoryDto> findPlayTimeDesc(
        final QuizSearchRepositoryCond condition,
        final Pageable pageable
    ) {
        return null;
    }

    /**
     * 플레이 횟수가 적은 순으로 퀴즈 정보를 담아주는 메서드
     * @param condition 검색 조건
     * @param pageable 페이징 검색 조건
     * @return  
     */
    @Override
    public List<QuizSearchRepositoryDto> findPlayTimeAsc(
        final QuizSearchRepositoryCond condition,
        final Pageable pageable
    ) {
        return null;
    }

    /**
     * 최근에 만들어진 순으로 퀴즈 정보를 담아주는 메서드
     * @param condition 검색 조건
     * @param pageable 페이징 검색 조건
     * @return
     */
    @Override
    public List<QuizSearchRepositoryDto> findRecentDesc(
        final QuizSearchRepositoryCond condition,
        final Pageable pageable
    ) {
        return null;
    }

    /**
     * 오래된 순으로 퀴즈 정보를 담아주는 메서드
     * @param condition 검색 조건
     * @param pageable 페이징 검색 조건
     * @return
     */
    @Override
    public List<QuizSearchRepositoryDto> findRecentAsc(
        final QuizSearchRepositoryCond condition,
        final Pageable pageable
    ) {
        return null;
    }
}
