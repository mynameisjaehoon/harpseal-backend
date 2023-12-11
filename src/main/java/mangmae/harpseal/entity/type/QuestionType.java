package mangmae.harpseal.entity.type;

import mangmae.harpseal.domain.exception.UnknownQuestionTypeException;

import java.util.Arrays;

public enum QuestionType {
    MULTIPLE,
    SUBJECTIVE;

    public static QuestionType by(String name) {
        return Arrays.stream(QuestionType.values())
                .filter(value -> value.toString().equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(() -> new UnknownQuestionTypeException("Can't find [" + name + "] question type"));
    }
}
