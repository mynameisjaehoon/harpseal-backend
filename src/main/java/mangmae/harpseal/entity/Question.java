package mangmae.harpseal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mangmae.harpseal.entity.type.QuestionType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    private String content;
    private int number;
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToOne(mappedBy = "question")
    private Attachment attachment;

    @OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<MultipleQuestionChoice> choices = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private QuestionType questionType;

    public Question(String content, int number, String answer, QuestionType questionType) {
        this.content = content;
        this.number = number;
        this.answer = answer;
        this.questionType = questionType;
    }
}
