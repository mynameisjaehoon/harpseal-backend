package mangmae.harpseal.domain.quiz.service;


import lombok.RequiredArgsConstructor;
import mangmae.harpseal.domain.exception.CannotFindQuizException;
import mangmae.harpseal.domain.quiz.dto.QuizSearchType;
import mangmae.harpseal.domain.quiz.repository.QuizRepository;
import mangmae.harpseal.domain.quiz.repository.dto.QuizSearchRepositoryCond;
import mangmae.harpseal.domain.quiz.repository.dto.QuizSearchRepositoryDto;
import mangmae.harpseal.domain.quiz.service.dto.QuizCreateServiceDto;
import mangmae.harpseal.domain.quiz.service.dto.QuizSearchServiceCond;
import mangmae.harpseal.domain.quiz.service.dto.QuizSearchServiceDto;
import mangmae.harpseal.domain.quiz.util.QuizValidator;
import mangmae.harpseal.entity.Quiz;
import mangmae.harpseal.util.FileUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizService {

    private final QuizRepository quizRepository;
    private final FileUtil fileUtil;

    @Transactional
    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Transactional
    public Quiz findById(Long id) {
        Optional<Quiz> findQuizOptional = quizRepository.findById(id);

        return findQuizOptional.orElseThrow(
            () -> new CannotFindQuizException("Can't find Quiz Entity with id=[" + id + "]")
        );
    }

    @Transactional
    public Quiz createQuiz(QuizCreateServiceDto dto) {
        QuizValidator.validateQuizRegistrationForm(dto); // 퀴즈 등록 폼 데이터 검증

        // 폼에서 데이터를 추출해 Quiz 엔티티를 만든다.
        //TODO 비밀번호는 추후 암호화
        String title = dto.getTitle();
        String description = dto.getDescription();
        String password = dto.getPassword();

        Quiz newQuiz = new Quiz(title, description, password);
        return quizRepository.save(newQuiz);
    }

    public Page<QuizSearchServiceDto> searchWithCondition(
        final QuizSearchServiceCond condition,
        final Pageable pageable
    ) {
        QuizSearchType searchType = condition.getSearchType();
        QuizSearchRepositoryCond repositoryCond = condition.toRepositoryCond();
        long offset = pageable.getOffset();
        int size = pageable.getPageSize();

        Page<QuizSearchRepositoryDto> result = null;
        switch (searchType) {
            case NONE, COUNT_ASC:
                result = quizRepository.findPlayTimeAsc(repositoryCond, pageable);
            case COUNT_DESC:
                result = quizRepository.findPlayTimeDesc(repositoryCond, pageable);
            case RECENT:
                result = quizRepository.findRecentDesc(repositoryCond, pageable);
            case OLD:
                result = quizRepository.findRecentAsc(repositoryCond, pageable);
                // TODO: 2023/12/13 올바르지 못한 타입이 입력되었을 때 어떻게 할것인가? 생각해보자. 단, 이 서비스 클래스에서 할일은 아닌 것 같다.
        }

        return result.map(dto -> QuizSearchServiceDto.builder()
            .id(dto.getId())
            .title(dto.getTitle())
            .description(dto.getDescription())
            .imageData(fileUtil.loadImageBase64(dto.getImagePath()))
            .build()
        );
    }

}
