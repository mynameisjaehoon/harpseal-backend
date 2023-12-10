package mangmae.harpseal.util;


import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

public class FilePathUtil {

    @Value("${file.thumbnail-image.path}")
    private String quizThumbnailImagePath;

    private FilePathUtil(){
    }

    public static FilePathUtil of() {
        return new FilePathUtil();
    }

    public String makeThumbnailImagePath() {
        StringBuilder imageNameBuilder = new StringBuilder(quizThumbnailImagePath);
        imageNameBuilder
                .append(UUID.randomUUID().toString().substring(0, 10))
                .append(".png");

        return imageNameBuilder.toString();
    }

}
