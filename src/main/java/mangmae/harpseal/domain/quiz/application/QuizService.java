package mangmae.harpseal.domain.quiz.application;


import lombok.RequiredArgsConstructor;
import mangmae.harpseal.domain.quiz.application.dto.*;
import mangmae.harpseal.domain.quiz.exception.CannotFindQuizException;
import mangmae.harpseal.domain.attachment.repository.AttachmentRepository;
import mangmae.harpseal.domain.question.repository.QuestionRepository;
import mangmae.harpseal.domain.question.dto.ChoiceEditServiceDto;
import mangmae.harpseal.domain.question.dto.QuestionEditServiceDto;
import mangmae.harpseal.domain.quiz.dto.QuizSearchType;
import mangmae.harpseal.domain.quiz.repository.QuizRepository;
import mangmae.harpseal.domain.quiz.repository.dto.QuizDeleteRepositoryResponse;
import mangmae.harpseal.domain.quiz.repository.dto.QuizSearchRepositoryCond;
import mangmae.harpseal.domain.quiz.repository.dto.QuizSearchRepositoryDto;
import mangmae.harpseal.domain.quiz.util.QuizValidator;
import mangmae.harpseal.domain.thumbnail.repository.ThumbnailRepository;
import mangmae.harpseal.global.entity.Attachment;
import mangmae.harpseal.global.entity.Question;
import mangmae.harpseal.global.entity.Quiz;
import mangmae.harpseal.global.entity.QuizThumbnail;
import mangmae.harpseal.global.entity.type.AttachmentType;
import mangmae.harpseal.global.util.FileUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static mangmae.harpseal.global.entity.type.QuestionType.MULTIPLE;
import static mangmae.harpseal.global.util.SecurityUtil.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final ThumbnailRepository thumbnailRepository;
    private final AttachmentRepository attachmentRepository;
    private final FileUtil fileUtil;

    @Transactional
    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public Quiz findById(Long id) {
        Optional<Quiz> findQuizOptional = quizRepository.findById(id);

        return findQuizOptional.orElseThrow(
            () -> new CannotFindQuizException("Can't find Quiz Entity with id=[" + id + "]")
        );
    }

    public boolean existsById(Long id) {
        return quizRepository.existsById(id);
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

    public QuizSimpleServiceDto findSingleQuiz(final SingleQuizServiceCond condition) {
        // 퀴즈 ID에 해당하는 퀴즈 정보를 받아온다.
        Long quizId = condition.getId();
        QuizSimpleRepositoryDto repositoryDto = quizRepository.findSingleQuiz(quizId);
        QuizSimpleServiceDto serviceDto = QuizSimpleServiceDto.fromRepositoryDto(repositoryDto);

        // 퀴즈의 썸네일을 지정한다.
        String path = repositoryDto.getThumbnailPath();
        serviceDto.addThumbnailData(path); // if path is null, the default image is applied
        return serviceDto;
    }

    @Transactional
    public QuizDeleteResponseDto deleteQuizById(Long id, String password) {
        Quiz quiz = findById(id);
        verifyPassword(quiz.getPassword(), password);
        QuizDeleteRepositoryResponse response = quizRepository.deleteQuizById(id);

        return QuizDeleteResponseDto.fromRepositoryDto(response);
    }


    /**
     * @param dto 퀴즈 수정 정보가 담긴 dto
     * @return 수정된 퀴즈의 PK
     */
    @Transactional
    public Quiz updateQuiz(QuizEditServiceRequestDto dto) {
        // 패스워드 확인
        Long quizId = dto.getId();
        String requestPassword = dto.getPassword();
        Quiz findQuiz = findById(quizId);
        verifyPassword(findQuiz.getPassword(), requestPassword);

        // 퀴즈 정보 수정
        findQuiz.changeTitle(dto.getTitle());
        findQuiz.changeDescription(dto.getDescription());

        return findQuiz;
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

        // 수정하고자 하는 Question 엔티티를 찾는다.
        int questionNumber = dto.getNumber();
        Question findQuestion = questionRepository.findQuestion(quizId, questionNumber);
        questionRepository.updateQuestion(dto.toRepositoryDto());

        // 선택지 정보를 수정한다.
        Long questionId = findQuestion.getId();
        List<ChoiceEditServiceDto> choices = dto.getChoices();
        questionRepository.deleteChoices(questionId); // 기존의 선택지 정보 삭제

        // 문제의 타입 정보가 객관식이고, 요청데이터에 선택지 데이터가 비어있지 않은 경우에만 선택지 데이터 삽입
        if (dto.getType() == MULTIPLE && dto.getChoices() != null) {
            for (ChoiceEditServiceDto choice : dto.getChoices()) {
                questionRepository.insertChoice(findQuestion, choice.toRepositoryDto());
            }
        }

        // 첨부파일 정보를 수정한다.
        Optional<Attachment> findAttachment = attachmentRepository.findAttachment(questionId);
        findAttachment.ifPresent((at) -> {
            attachmentRepository.delete(at);
            fileUtil.deleteFile(at.getFilePath());
        });

        if (!attachment.isEmpty()) {
            // TODO: 2023/12/18 이미지 말고 다른 타입도 대응해야한다.
            String savedPath = fileUtil.makeQuestionImagePath();
            Attachment newAttachment = new Attachment(AttachmentType.IMAGE, savedPath);
            findQuestion.changeAttachment(newAttachment);
        }
    }

    @Transactional
    public void addQuizLike(Long quizId) {
        Quiz findQuiz = findById(quizId);
        findQuiz.addLikeCount();
    }
}
