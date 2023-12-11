package mangmae.harpseal.config.quiz;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mangmae.harpseal.entity.Quiz;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitQuizService {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void init() {
        Quiz quiz1 = new Quiz("init quiz 1", "this is init quiz1", "12345");
        Quiz quiz2 = new Quiz("init quiz 2", "this is init quiz2", "12345");
        Quiz quiz3 = new Quiz("init quiz 3", "this is init quiz3", "12345");

        em.persist(quiz1);
        em.persist(quiz2);
        em.persist(quiz3);

        em.flush();
        em.clear();

    }

}
