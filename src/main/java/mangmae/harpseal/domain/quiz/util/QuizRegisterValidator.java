package mangmae.harpseal.domain.quiz.util;

import mangmae.harpseal.domain.dto.QuestionRegistrationFormDto;
import mangmae.harpseal.domain.dto.QuizRegistrationFormDto;
import mangmae.harpseal.domain.quiz.exception.QuizFormNotValidException;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 퀴즈 등록 검증 클래스
 */
public class QuizRegisterValidator {

    private final static int MINIMUM_QUIZ_TITLE_LENGTH = 5;
    private final static int MINIMUM_PASSWORD_LENGTH = 5;
    private final static int MINIMUM_DESCRIPTION_LENGTH = 10;
    private final static int MAXIMUM_DESCRIPTION_LENGTH = 100;
    private final static int MINIMUM_QUESTION_COUNT = 3;
    private final static int MAXIMUM_QUESTION_COUNT = 30;

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
        List<QuestionRegistrationFormDto> questions = form.getQuestions();

        if (!StringUtils.hasText(title) || title.length() < MINIMUM_QUIZ_TITLE_LENGTH) {
            throw new QuizFormNotValidException("The quiz title must be at least 5 characters long.");
        }

        if (!StringUtils.hasText(password) || password.length() < MINIMUM_PASSWORD_LENGTH) {
            throw new QuizFormNotValidException("Passwords must be at least five characters long.");
        }

        if (!StringUtils.hasText(description) || description.length() < MINIMUM_DESCRIPTION_LENGTH || description.length() > MAXIMUM_DESCRIPTION_LENGTH) {
            throw new QuizFormNotValidException("The description of the quiz must be between 5 and 100 characters.");
        }

        if (questions.size() < MINIMUM_QUESTION_COUNT || questions.size() > MAXIMUM_QUESTION_COUNT) {
            throw new QuizFormNotValidException("The number of questions should be no more than 3 and no less than 30.");
        }
    }
}
