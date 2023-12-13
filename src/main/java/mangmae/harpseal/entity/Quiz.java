package mangmae.harpseal.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mangmae.harpseal.entity.auditing.CreatedDateEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"title", "description", "password", "likeCount", "playTime"})
public class Quiz extends CreatedDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private Long id;
    private String title;
    private String description;
    private String password;
    private Integer likeCount;
    private Integer playTime;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<QuizResult> quizResults = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToOne(mappedBy = "quiz", fetch = FetchType.LAZY)
    private QuizThumbnail thumbnail;


    public Quiz(String title, String description, String password) {
        this.title = title;
        this.description = description;
        this.password = password;
        this.likeCount = 0;
        this.playTime = 0;
    }

    public void addQuestion(Question question) {
        questions.add(question);
        question.changeQuiz(this);
    }

    public void addThumbnail(QuizThumbnail thumbnail) {
        this.thumbnail = thumbnail;
        thumbnail.setQuiz(this);
    }

    public void addPlayTime() {
        playTime++;
    }

}
