package mangmae.harpseal.domain.quiz.util;

import mangmae.harpseal.domain.quiz.service.dto.question.QuestionCreateServiceDto;
import mangmae.harpseal.domain.exception.QuestionFormNotValidException;
import mangmae.harpseal.entity.type.QuestionType;
import org.springframework.util.StringUtils;

import java.util.Arrays;

public class QuestionValidator {

    // question validation constant
    private final static int MINIMUM_QUESTION_LENGTH = 5;
    private final static int MAXIMUM_QUESTION_LENGTH = 200;

    /**
     * 올바른 문제 등록 폼인지 검사하는 메서드
     * - 문제의 길이가 5글자이상, 200글자 이하인가?
     * -
     * @param form 문제(Question) 등록 폼
     */
    public static void validateQuestionRegistrationForm(QuestionCreateServiceDto form) {
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
