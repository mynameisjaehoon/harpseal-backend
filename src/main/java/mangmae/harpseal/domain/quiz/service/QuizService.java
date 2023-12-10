package mangmae.harpseal.domain.quiz.service;


import lombok.RequiredArgsConstructor;
import mangmae.harpseal.domain.dto.QuizRegistrationFormDto;
import mangmae.harpseal.domain.quiz.exception.ThumbnailImageStoreException;
import mangmae.harpseal.domain.quiz.repository.QuizRepository;
import mangmae.harpseal.domain.quiz.repository.ThumbnailRepository;
import mangmae.harpseal.domain.quiz.util.QuizRegisterValidator;
import mangmae.harpseal.entity.Quiz;
import mangmae.harpseal.entity.QuizThumbnail;
import mangmae.harpseal.util.FilePathUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizService {

    private final QuizRepository quizRepository;
    private final ThumbnailRepository thumbnailRepository;
    private final FilePathUtil filePathUtil;

    @Transactional
    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Transactional
    public Quiz createQuiz(QuizRegistrationFormDto form) {
        QuizRegisterValidator.validateQuizRegistrationForm(form); // 퀴즈 등록 폼 데이터 검증

        // 폼에서 데이터를 추출해 Quiz 엔티티를 만든다.
        //TODO 비밀번호는 추후 암호화
        String title = form.getTitle();
        String description = form.getDescription();
        String password = form.getPassword();

        Quiz newQuiz = new Quiz(title, description, password);
        Quiz savedQuiz = quizRepository.save(newQuiz);

        return savedQuiz;
    }

    @Transactional
    public QuizThumbnail storeQuizThumbnailImage(Quiz quiz, MultipartFile thumbnailImage) {
        if (thumbnailImage.isEmpty()) {
            return null;
        }

        String storeImagePath = filePathUtil.makeThumbnailImagePath();
        try {
            thumbnailImage.transferTo(new File(storeImagePath)); //이미지 파일 저장

            //썸네일 데이터 저장
            QuizThumbnail savedThumbnail = thumbnailRepository.save(new QuizThumbnail(storeImagePath));
            quiz.addThumbnail(savedThumbnail);
            return savedThumbnail;
        } catch (IOException e) {
            throw new ThumbnailImageStoreException("Fail to store quiz thumbnail image", e);
        }

    }

}
