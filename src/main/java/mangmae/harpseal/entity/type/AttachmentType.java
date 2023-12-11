package mangmae.harpseal.entity.type;

import mangmae.harpseal.domain.exception.UnknownAttachmentTypeException;
import mangmae.harpseal.domain.exception.UnknownQuestionTypeException;

import java.util.Arrays;

public enum AttachmentType {
    NONE, IMAGE, SOUND;

    public static AttachmentType by(String name) {
        return Arrays.stream(AttachmentType.values())
            .filter(value -> value.toString().equalsIgnoreCase(name))
            .findAny()
            .orElseThrow(() -> new UnknownAttachmentTypeException("Can't find [" + name + "] attachment type"));
    }

}
