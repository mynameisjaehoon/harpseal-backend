package mangmae.harpseal.global.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mangmae.harpseal.global.entity.auditing.CreatedDateEntity;

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

    @OneToOne(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private QuizThumbnail thumbnail;


    public Quiz(String title, String description, String password) {
        this.title = title;
        this.description = description;
        this.password = password;
        this.likeCount = 0;
        this.playTime = 0;
    }

    // 편의 메서드
    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void addQuestion(Question question) {
        questions.add(question);
        question.changeQuiz(this);
    }

    public void changeThumbnail(QuizThumbnail thumbnail) {
        this.thumbnail = thumbnail;
        thumbnail.changeQuiz(this);
    }

    public void addPlayTime() {
        playTime++;
    }

    public void addLikeCount() {
        likeCount++;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.changeQuiz(this);
    }

}
