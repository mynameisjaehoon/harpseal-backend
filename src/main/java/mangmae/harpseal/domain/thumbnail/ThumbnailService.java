package mangmae.harpseal.domain.thumbnail;

import lombok.RequiredArgsConstructor;
import mangmae.harpseal.domain.exception.ThumbnailImageStoreException;
import mangmae.harpseal.entity.Quiz;
import mangmae.harpseal.entity.QuizThumbnail;
import mangmae.harpseal.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThumbnailService {

    private final ThumbnailRepository thumbnailRepository;
    private final FileUtil filePathUtil;

    /**
     * 연관된 퀴즈와 저장할 이미지 데이터를 전달받아 새롭게 생성된 퀴즈에 썸네일 이미지를 저장하는 메서드<br>
     * @param quiz 새롭게 생성된 퀴즈 엔티티
     * @param thumbnailImage 저장해야하는 썸네일 이미지
     * @return 생성된 퀴즈 썸네일 엔티티 옵셔널을 반환한다.
     */
    @Transactional
    public Optional<QuizThumbnail> storeQuizThumbnailImage(Quiz quiz, MultipartFile thumbnailImage) {
        if (thumbnailImage.isEmpty()) {
            return Optional.empty();
        }

        String storeImagePath = filePathUtil.makeThumbnailImagePath();
        try {
            thumbnailImage.transferTo(new File(storeImagePath)); //이미지 파일 저장

            //썸네일 데이터 저장
            QuizThumbnail savedThumbnail = thumbnailRepository.save(new QuizThumbnail(storeImagePath));
            quiz.addThumbnail(savedThumbnail);
            return Optional.of(savedThumbnail);
        } catch (IOException e) {
            throw new ThumbnailImageStoreException("Fail to store quiz thumbnail image", e);
        }

    }

}
