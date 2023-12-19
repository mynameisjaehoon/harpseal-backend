package mangmae.harpseal.global.entity.type;

import mangmae.harpseal.domain.question.exception.UnknownQuestionTypeException;

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

    public static String getNameOf(QuestionType type) {
        return type.toString();
    }
}
