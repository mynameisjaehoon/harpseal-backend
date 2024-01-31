package mangmae.harpseal.domain.thumbnail.application;

import lombok.RequiredArgsConstructor;
import mangmae.harpseal.domain.thumbnail.dto.ThumbnailServiceResponseDto;
import mangmae.harpseal.domain.thumbnail.exception.ThumbnailImageStoreException;
import mangmae.harpseal.domain.thumbnail.repository.ThumbnailRepository;
import mangmae.harpseal.global.entity.Quiz;
import mangmae.harpseal.global.entity.QuizThumbnail;
import mangmae.harpseal.global.util.FileUtil;
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
    private final FileUtil fileUtil;

    public void save(QuizThumbnail thumbnail) {
        thumbnailRepository.save(thumbnail);
    }

    @Transactional
    public Optional<QuizThumbnail> findByQuizId(Long quizId) {
        return thumbnailRepository.findByQuizId(quizId);
    }

    /**
     * 연관된 퀴즈와 저장할 이미지 데이터를 전달받아 새롭게 생성된 퀴즈에 썸네일 이미지를 저장하는 메서드<br>
     * @param quiz 새롭게 생성된 퀴즈 엔티티
     * @param thumbnailImage 저장해야하는 썸네일 이미지
     * @return 생성된 퀴즈 썸네일 엔티티 옵셔널을 반환한다.
     */
    @Transactional
    public Optional<QuizThumbnail> store(Quiz quiz, MultipartFile thumbnailImage) {
        if (thumbnailImage.isEmpty()) {
            return Optional.empty();
        }

        String storeImagePath = fileUtil.makeThumbnailImagePath();
        try {
            thumbnailImage.transferTo(new File(storeImagePath)); //이미지 파일 저장

            //썸네일 데이터 저장
            QuizThumbnail thumbnail = new QuizThumbnail(storeImagePath);
            thumbnail.changeQuiz(quiz);
            QuizThumbnail savedThumbnail = thumbnailRepository.save(thumbnail);
            return Optional.of(savedThumbnail);
        } catch (IOException e) {
            throw new ThumbnailImageStoreException("Fail to store quiz thumbnail image", e);
        }

    }


    public void delete(Long thumbnailId) {
        thumbnailRepository.deleteById(thumbnailId);
    }
}
