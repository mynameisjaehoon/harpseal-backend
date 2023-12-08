package mangmae.harpseal.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mangmae.harpseal.entity.auditing.CreatedDateEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quiz extends CreatedDateEntity {

    @Id
    @GeneratedValue
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thumbnail_id")
    private QuizThumbnail thumbnail;

}
