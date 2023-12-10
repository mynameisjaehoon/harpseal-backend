package mangmae.harpseal.domain.quiz.service;


import lombok.RequiredArgsConstructor;
import mangmae.harpseal.domain.quiz.dto.QuizCreateRequestForm;
import mangmae.harpseal.domain.exception.CannotFindQuizException;
import mangmae.harpseal.domain.question.QuestionRepository;
import mangmae.harpseal.domain.quiz.repository.QuizRepository;
import mangmae.harpseal.domain.thumbnail.ThumbnailRepository;
import mangmae.harpseal.domain.quiz.util.QuizValidator;
import mangmae.harpseal.entity.Quiz;
import mangmae.harpseal.util.FilePathUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizService {

    private final QuizRepository quizRepository;
    
    @Transactional
    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Transactional
    public Quiz findById(Long id) {
        Optional<Quiz> findQuizOptional = quizRepository.findById(id);

        return findQuizOptional.orElseThrow(
            () -> new CannotFindQuizException("Can't find Quiz Entity with id=[" + id + "]")
        );
    }

    @Transactional
    public Quiz createQuiz(QuizServiceDto dto) {
        QuizValidator.validateQuizRegistrationForm(dto); // 퀴즈 등록 폼 데이터 검증

        // 폼에서 데이터를 추출해 Quiz 엔티티를 만든다.
        //TODO 비밀번호는 추후 암호화
        String title = dto.getTitle();
        String description = dto.getDescription();
        String password = dto.getPassword();

        Quiz newQuiz = new Quiz(title, description, password);
        return quizRepository.save(newQuiz);
    }

}
