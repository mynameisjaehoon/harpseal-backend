package mangmae.harpseal.config.quiz;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * 애플리케이션 로컬에서 사용할 퀴즈 데이터를 초기화 하는 클래스
 */
@Profile("local")
@Component
@RequiredArgsConstructor
public class InitQuiz {

    private final InitQuizService initQuizService;


    @PostConstruct
    public void init() {
        initQuizService.init();
    }
}
