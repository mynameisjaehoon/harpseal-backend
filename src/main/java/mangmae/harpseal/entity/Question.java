package mangmae.harpseal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mangmae.harpseal.entity.type.QuestionType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToOne(mappedBy = "question")
    private Attachment attachment;

    @OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<MultipleQuestionChoice> choices = new ArrayList<>();

    private String content;
    private int number;

    @Enumerated(value = EnumType.STRING)
    private QuestionType questionType;

    private String answer;
}
