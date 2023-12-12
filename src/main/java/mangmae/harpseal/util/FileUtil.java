package mangmae.harpseal.util;


import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

public class FilePathUtil {

    @Value("${file.thumbnail-image.path}")
    private String quizThumbnailImagePath;

    @Value("${file.question-image.path}")
    private String questionImagePath;

    @Value("${file.question-sound.path}")
    private String questionSoundPath;

    private FilePathUtil(){
    }

    public static FilePathUtil of() {
        return new FilePathUtil();
    }

    // TODO: 2023/12/11 이미지 타입 호환 고려, MultiPartFile 로부터 확장자 정보를 가져와야하나?
    public String makeThumbnailImagePath() {
        StringBuilder imageNameBuilder = new StringBuilder(quizThumbnailImagePath);
        return imageNameBuilder
            .append(UUID.randomUUID().toString().substring(0, 10))
            .append(".png")
            .toString();
    }

    public String makeQuestionImagePath() {
        StringBuilder imageNameBuilder = new StringBuilder(questionImagePath);
        return imageNameBuilder
            .append(UUID.randomUUID().toString().substring(0, 10))
            .append(".png")
            .toString();
    }

    public String makeQuestionSoundPath() {
        StringBuilder soundNameBuilder = new StringBuilder(questionSoundPath);
        return soundNameBuilder
            .append(UUID.randomUUID().toString().substring(0, 10))
            .append(".mp3")
            .toString();
    }

}
