package mangmae.harpseal.domain.application;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mangmae.harpseal.domain.attachment.application.AttachmentService;
import mangmae.harpseal.domain.choice.application.ChoiceService;
import mangmae.harpseal.domain.choice.dto.ChoiceServiceDto;
import mangmae.harpseal.domain.question.application.QuestionService;
import mangmae.harpseal.domain.question.dto.QuestionCreateServiceDto;
import mangmae.harpseal.domain.question.dto.QuestionServiceDto;
import mangmae.harpseal.domain.quiz.application.QuizService;
import mangmae.harpseal.domain.question.dto.QuestionSimpleServiceDto;
import mangmae.harpseal.domain.quiz.application.dto.QuizCreateServiceDto;
import mangmae.harpseal.domain.question.util.QuestionValidator;
import mangmae.harpseal.domain.quiz.application.dto.SingleQuizServiceCond;
import mangmae.harpseal.domain.quiz.application.dto.QuizSimpleServiceDto;
import mangmae.harpseal.domain.quiz.application.dto.SingleQuizServiceResponse;
import mangmae.harpseal.global.entity.type.AttachmentType;
import mangmae.harpseal.global.util.SecurityUtil;
import mangmae.harpseal.domain.thumbnail.application.ThumbnailService;
import mangmae.harpseal.global.entity.MultipleQuestionChoice;
import mangmae.harpseal.global.entity.Question;
import mangmae.harpseal.global.entity.Quiz;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
    private final AttachmentService attachmentService;

    /**
     * 퀴즈를 생성하는 메서드
     * @return 생성된 엔티티
     */
    @Transactional
    public Quiz createQuiz(
        final QuizCreateServiceDto dto,
        final MultipartFile thumbnailImage
    ) {
        Quiz createdQuiz = quizService.createQuiz(dto);
        thumbnailService.storeQuizThumbnailImage(createdQuiz, thumbnailImage);
        return createdQuiz;

    }

    /**
     * @param quizId 문제가 생성될 퀴즈의 id
     * @param dto 문제 생성 요청 폼
     * @param file 첨부파일
     * @return 생성된 Question 엔티티
     */
    @Transactional
    public Question createQuestion(
        final Long quizId,
        final QuestionCreateServiceDto dto,
        final MultipartFile file
    ) {
// 폼데이터 올바른지 확인
        QuestionValidator.validateQuestionRegistrationForm(dto);

        Quiz findQuiz = quizService.findById(quizId);
        SecurityUtil.verifyPassword(dto.getPassword(), findQuiz.getPassword());

        Question savedQuestion = questionService.save(new Question(dto));
        findQuiz.addQuestion(savedQuestion); // 퀴즈와 연관관계 바인딩

        attachmentService.storeAttachment(
            savedQuestion,
            AttachmentType.by(dto.getAttachmentType()),
            file
        );

        choiceService.storeChoices(savedQuestion, dto);
        List<ChoiceServiceDto> choices = dto.getChoices();

        choices.stream()
            .map(ChoiceServiceDto::getContent)
            .map(MultipleQuestionChoice::new)
            .forEach((choice) -> {
                choiceService.save(choice);
                savedQuestion.addChoice(choice);
            });

        return savedQuestion;
    }

    public SingleQuizServiceResponse findSingleQuiz(final SingleQuizServiceCond condition) {
        QuizSimpleServiceDto quiz = quizService.findSingleQuiz(condition);
        List<QuestionSimpleServiceDto> simpleQuestions = questionService.findQuizQuestions(quiz.getId());

        List<QuestionServiceDto> questions = new ArrayList<>();
        for (QuestionSimpleServiceDto question : simpleQuestions) {
            // 문제마다 관련된 선택지 목록을 가져와 적용한다.
            Long questionId = question.getId();
            List<ChoiceServiceDto> choices = choiceService.findQuestionChoices(questionId);
            QuestionServiceDto questionServiceDto = QuestionServiceDto.fromSimpleServiceDto(question);
            if (choices != null) {
                questionServiceDto.addChoices(choices);
            }

            questions.add(questionServiceDto);
        }

        return SingleQuizServiceResponse.builder()
            .quizId(quiz.getId())
            .title(quiz.getTitle())
            .description(quiz.getDescription())
            .thumbnailData(quiz.getThumbnailData())
            .questions(questions)
            .build();
    }

}
