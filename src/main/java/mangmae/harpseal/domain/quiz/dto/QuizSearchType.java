package mangmae.harpseal.domain.quiz.dto;

import mangmae.harpseal.domain.quiz.exception.UnknownQuizSearchConditionException;

import java.util.Arrays;

public enum QuizSearchType {
    COUNT_ASC,
    COUNT_DESC,
    RECENT,
    OLD;

    public static QuizSearchType by(String type) {
        return Arrays.stream(QuizSearchType.values())
            .filter(value -> value.toString().equalsIgnoreCase(type))
            .findAny()
            .orElseThrow(() -> new UnknownQuizSearchConditionException(type));
    }
}
