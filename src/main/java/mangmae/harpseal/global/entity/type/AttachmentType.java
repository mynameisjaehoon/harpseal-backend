package mangmae.harpseal.global.entity.type;

import mangmae.harpseal.domain.attachment.exception.UnknownAttachmentTypeException;

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
