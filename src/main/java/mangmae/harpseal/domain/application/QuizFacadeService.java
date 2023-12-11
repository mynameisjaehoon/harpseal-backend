package mangmae.harpseal.domain.application;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mangmae.harpseal.domain.choice.ChoiceRepository;
import mangmae.harpseal.domain.choice.ChoiceService;
import mangmae.harpseal.domain.choice.dto.ChoiceServiceDto;
import mangmae.harpseal.domain.exception.QuizPasswordNotMatchException;
import mangmae.harpseal.domain.question.QuestionService;
import mangmae.harpseal.domain.question.QuestionServiceDto;
import mangmae.harpseal.domain.choice.dto.ChoiceCreateDto;
import mangmae.harpseal.domain.quiz.service.QuizService;
import mangmae.harpseal.domain.quiz.service.QuizServiceDto;
import mangmae.harpseal.domain.quiz.util.QuestionValidator;
import mangmae.harpseal.domain.quiz.util.SecurityUtil;
import mangmae.harpseal.domain.thumbnail.ThumbnailService;
import mangmae.harpseal.entity.MultipleQuestionChoice;
import mangmae.harpseal.entity.Question;
import mangmae.harpseal.entity.Quiz;
import mangmae.harpseal.entity.type.QuestionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizFacadeService {

    private final QuizService quizService;
    private final ThumbnailService thumbnailService;
    private final QuestionService questionService;
    private final ChoiceService choiceService;

    /**
     * 퀴즈를 생성하는 메서드
     * @return 생성된 엔티티
     */
    @Transactional
    public Quiz createQuiz(
        final QuizServiceDto dto,
        final MultipartFile thumbnailImage
    ) {
        Quiz createdQuiz = quizService.createQuiz(dto);
        thumbnailService.storeQuizThumbnailImage(createdQuiz, thumbnailImage);
        return createdQuiz;

    }

    /**
     * @param quizId 문제가 생성될 퀴즈의 id
     * @param dto 문제 생성 요청 폼
     * @param attachment 첨부파일
     * @return 생성된 Question 엔티티
     */
    @Transactional
    public Question createQuestion(
        final Long quizId,
        final QuestionServiceDto dto,
        final MultipartFile attachment
    ) {
// 폼데이터 올바른지 확인
        // TODO: 2023/12/11 컨트롤러에 받는 폼 DTO에 의존하는 것이 맞는지 고민
        QuestionValidator.validateQuestionRegistrationForm(dto);

        // 퀴즈 ID와 관련된 퀴즈를 찾는다.
        Quiz findQuiz = quizService.findById(quizId);

        // 비밀번호 일치여부 확인
        String password = dto.getPassword();
        String quizPassword = findQuiz.getPassword();

        if (!SecurityUtil.isPasswordEqual(password, quizPassword)) {
            throw new QuizPasswordNotMatchException("Quiz password not match");
        }

        // Question 엔티티를 만들고 퀴즈와 연관관계 바인딩
        // TODO: 2023/12/11 서비스 계층에 의존하는 dto 객체를 만들어서 전달한다.
        String content = dto.getContent();
        int number = dto.getNumber();
        String answer = dto.getAnswer();
        QuestionType type = QuestionType.by(dto.getType());
        Question newQuestion = new Question(content, number, answer, type);

        Question savedQuestion = questionService.save(newQuestion);
        findQuiz.addQuestion(savedQuestion); // 퀴즈와 연관관계 바인딩

        // 문제와 연관관계에 있는 첨부파일을 저장한다.


        // 문제와 연관관계에 있는 선지(choice)들을 저장한다.
        List<ChoiceServiceDto> choices = dto.getChoices();
        log.info("new choices = {}", choices);

        for (ChoiceServiceDto choice : choices) {
            String choiceContent = choice.getContent();
            MultipleQuestionChoice newChoice = new MultipleQuestionChoice(choiceContent);
            choiceService.save(newChoice);
        }

//        choices.stream()
//            .map(ChoiceServiceDto::getContent)
//            .map(content -> new MultipleQuestionChoice(content))
//            .forEach(choiceService::save);

        return savedQuestion;
    }



}
