package mangmae.harpseal.entity;

import jakarta.persistence.*;
import mangmae.harpseal.entity.auditing.CreatedDateEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Quiz extends CreatedDateEntity {

    @Id
    @GeneratedValue
    @Column(name = "quiz_id")
    private Long id;
    private String title;
    private String password;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<QuizResult> quizResults = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Comment> comments;

}
