package mangmae.harpseal.entity.type;

import mangmae.harpseal.domain.exception.CannotFindQuestionTypeException;

import java.util.Arrays;

public enum QuestionType {
    MULTIPLE,
    SUBJECTIVE;

    public static QuestionType by(String name) {
        return Arrays.stream(QuestionType.values())
                .filter(value -> value.toString().equals(name))
                .findAny()
                .orElseThrow(() -> new CannotFindQuestionTypeException("Can't find [" + name + "] question type"));
    }
}
