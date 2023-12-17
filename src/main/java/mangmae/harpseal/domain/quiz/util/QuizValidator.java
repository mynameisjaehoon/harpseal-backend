package mangmae.harpseal.domain.quiz.util;

import mangmae.harpseal.domain.exception.QuizFormNotValidException;
import mangmae.harpseal.domain.quiz.service.dto.quiz.QuizCreateServiceDto;
import org.springframework.util.StringUtils;

/**
 * 퀴즈 등록 검증 클래스
 */
public class QuizValidator {

    // quiz validation constant
    private final static int MINIMUM_QUIZ_TITLE_LENGTH = 5;
    private final static int MINIMUM_PASSWORD_LENGTH = 5;
    private final static int MINIMUM_DESCRIPTION_LENGTH = 10;
    private final static int MAXIMUM_DESCRIPTION_LENGTH = 100;



    private QuizValidator() {
    }

    /**
     * 퀴즈 등록 폼의 형식이 올바른지 검사하는 메서드<br>
     * - 퀴즈의 제목은 5글자 이상이어야 한다.<br>
     * - 퀴즈의 비밀번호는 5글자 이상이어야 한다.<br>
     * - 퀴즈의 설명은 5글자 이상, 100글자 이하여야 한다.<br>
     * - 퀴즈의 문제 수는 3개 이상, 30개 이하여야 한다.<br>
     * @param dto 퀴즈 등록 폼 DTO
     */
    public static void validateQuizRegistrationForm(QuizCreateServiceDto dto) {
        String title = dto.getTitle();
        String password = dto.getPassword();
        String description = dto.getDescription();

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


}
