package mangmae.harpseal.domain.quiz.util;

import mangmae.harpseal.domain.dto.QuestionRegistrationFormDto;
import mangmae.harpseal.domain.dto.QuizRegistrationFormDto;
import mangmae.harpseal.domain.quiz.exception.QuestionFormNotValidException;
import mangmae.harpseal.domain.quiz.exception.QuizFormNotValidException;
import mangmae.harpseal.entity.type.QuestionType;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 퀴즈 등록 검증 클래스
 */
public class QuizRegisterValidator {

    // quiz validation constant
    private final static int MINIMUM_QUIZ_TITLE_LENGTH = 5;
    private final static int MINIMUM_PASSWORD_LENGTH = 5;
    private final static int MINIMUM_DESCRIPTION_LENGTH = 10;
    private final static int MAXIMUM_DESCRIPTION_LENGTH = 100;
    private final static int MINIMUM_QUESTION_COUNT = 3;
    private final static int MAXIMUM_QUESTION_COUNT = 30;

    // question validation constant
    private final static int MINIMUM_QUESTION_LENGTH = 5;
    private final static int MAXIMUM_QUESTION_LENGTH = 5;

    private QuizRegisterValidator() {
    }

    /**
     * 퀴즈 등록 폼의 형식이 올바른지 검사하는 메서드<br>
     * - 퀴즈의 제목은 5글자 이상이어야 한다.<br>
     * - 퀴즈의 비밀번호는 5글자 이상이어야 한다.<br>
     * - 퀴즈의 설명은 5글자 이상, 100글자 이하여야 한다.<br>
     * - 퀴즈의 문제 수는 3개 이상, 30개 이하여야 한다.<br>
     * @param form 퀴즈 등록 폼 DTO
     */
    public static void validateQuizRegistrationForm(QuizRegistrationFormDto form) {
        String title = form.getTitle();
        String password = form.getPassword();
        String description = form.getDescription();

        if (!StringUtils.hasText(title) || title.length() < MINIMUM_QUIZ_TITLE_LENGTH) {
            throw new QuizFormNotValidException("The quiz title must be at least 5 characters long.");
        }

        if (!StringUtils.hasText(password) || password.length() < MINIMUM_PASSWORD_LENGTH) {
            throw new QuizFormNotValidException("Passwords must be at least five characters long.");
        }

        if (!StringUtils.hasText(description) || description.length() < MINIMUM_DESCRIPTION_LENGTH || description.length() > MAXIMUM_DESCRIPTION_LENGTH) {
            throw new QuizFormNotValidException("The description of the quiz must be between 5 and 100 characters.");
        }

    }

    public static void validateQuestionRegistrationForm(QuestionRegistrationFormDto form) {
        String content = form.getContent();
        String type = form.getType();
        String answer = form.getAnswer();

        if (!StringUtils.hasText(content) || content.length() < MINIMUM_QUESTION_LENGTH || content.length() > MAXIMUM_QUESTION_LENGTH) {
            throw new QuestionFormNotValidException("Question must be at least 5 characters long and no more than 200 characters.");
        }

        if (!StringUtils.hasText(type) || !Arrays.toString(QuestionType.values()).contains(type)) {
            throw new QuestionFormNotValidException("The type of Question is required. (MULTIPLE, SUBJECTIVE)");
        }

        if (!StringUtils.hasText(answer)) {
            throw new QuestionFormNotValidException("The answer of Question is required");
        }
    }
}
