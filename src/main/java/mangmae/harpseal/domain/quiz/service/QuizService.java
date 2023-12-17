package mangmae.harpseal.domain.quiz.service;


import lombok.RequiredArgsConstructor;
import mangmae.harpseal.domain.choice.dto.ChoiceServiceDto;
import mangmae.harpseal.domain.exception.CannotFindQuizException;
import mangmae.harpseal.domain.quiz.repository.dto.question.QuestionRepositoryDto;
import mangmae.harpseal.domain.quiz.service.dto.question.QuestionEditServiceDto;
import mangmae.harpseal.domain.quiz.service.dto.question.QuestionServiceDto;
import mangmae.harpseal.domain.quiz.service.dto.quiz.*;
import mangmae.harpseal.domain.quiz.dto.QuizSearchType;
import mangmae.harpseal.domain.quiz.repository.jpainterface.quiz.QuizRepository;
import mangmae.harpseal.domain.quiz.repository.dto.quiz.QuizDeleteRepositoryResponse;
import mangmae.harpseal.domain.quiz.repository.dto.quiz.QuizSearchRepositoryCond;
import mangmae.harpseal.domain.quiz.repository.dto.quiz.QuizSearchRepositoryDto;
import mangmae.harpseal.domain.quiz.repository.dto.quiz.SingleQuizRepositoryResponse;
import mangmae.harpseal.domain.quiz.util.QuizValidator;
import mangmae.harpseal.domain.quiz.repository.jpainterface.thumbnail.ThumbnailRepository;
import mangmae.harpseal.entity.Quiz;
import mangmae.harpseal.entity.QuizThumbnail;
import mangmae.harpseal.util.FileUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static mangmae.harpseal.util.SecurityUtil.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizService {

    private final QuizRepository quizRepository;
    private final ThumbnailRepository thumbnailRepository;
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

    public SingleQuizServiceResponse findSingleQuiz(final SingleQuizServiceCond condition) {
        Long quizId = condition.getId();
        SingleQuizRepositoryResponse response = quizRepository.findSingleQuizById(quizId);

        String thumbnailData = fileUtil.loadImageBase64(response.getThumbnailPath());

        List<QuestionServiceDto> questions = new ArrayList<>();
        List<QuestionRepositoryDto> repositoryQuestions = response.getQuestions();

        repositoryQuestions
                .forEach(dto -> {
                    List<ChoiceServiceDto> choices = dto.getChoices().stream()
                            .map(ChoiceServiceDto::fromRepositoryDto)
                            .toList();

                    String attachmentData = fileUtil.loadImageBase64(dto.getAttachmentPath());
                    QuestionServiceDto serviceDto = QuestionServiceDto.fromRepositoryDto(dto, attachmentData, choices);
                    questions.add(serviceDto);
                });
        return SingleQuizServiceResponse.fromRepositoryResponse(response, thumbnailData, questions);
    }

    @Transactional
    public QuizDeleteResponseDto deleteQuizById(Long id, String password) {
        Quiz quiz = findById(id);
        verifyPassword(quiz.getPassword(), password);
        QuizDeleteRepositoryResponse response = quizRepository.deleteQuizById(id);

        return QuizDeleteResponseDto.fromRepositoryDto(response);
    }

    @Transactional
    public QuizEditServiceResponse editQuiz(
        final QuizEditServiceDto dto,
        final MultipartFile thumbnailRequest
    ) {
        // 패스워드 검사
        Quiz quiz = findById(dto.getId());
        verifyPassword(quiz.getPassword(), dto.getPassword());

        // 퀴즈 데이터 업데이트
        quizRepository.updateQuiz(dto.toRepositoryDto());

        // 썸네일 데이터 수정
        Optional<QuizThumbnail> findThumbnail = thumbnailRepository.findByQuizId(dto.getId());
        findThumbnail.ifPresent((th) -> {
            thumbnailRepository.delete(th);
            fileUtil.deleteFile(th.getFilePath());
        });
        String savedPath = fileUtil.saveThumbnailImage(thumbnailRequest);

        if (!thumbnailRequest.isEmpty()) {
            QuizThumbnail newThumbnail = new QuizThumbnail(savedPath);
            quiz.changeThumbnail(newThumbnail);
            thumbnailRepository.save(newThumbnail);
        }

        return QuizEditServiceResponse.builder()
            .id(dto.getId())
            .title(dto.getTitle())
            .description(dto.getDescription())
            .thumbnailImage(fileUtil.loadImageBase64(savedPath))
            .message("퀴즈가 수정되었습니다.")
            .build();
    }

    @Transactional
    public void editQuestion(
        final QuestionEditServiceDto dto,
        final MultipartFile attachment
    ) {
        // 비밀번호 검사
        Long quizId = dto.getQuizId();
        Quiz quiz = findById(quizId);
        verifyPassword(quiz.getPassword(), dto.getPassword());





    }
}
