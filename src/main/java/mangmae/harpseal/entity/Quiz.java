package mangmae.harpseal.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.entity.auditing.CreatedDateEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Quiz extends CreatedDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private Long id;
    private String title;
    private String description;
    private String password;

    public Quiz(String title, String password) {
        this.title = title;
        this.password = password;
    }

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<QuizResult> quizResults = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToOne(mappedBy = "quiz", fetch = FetchType.LAZY)
    private QuizThumbnail thumbnail;

}
