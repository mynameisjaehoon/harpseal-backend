package mangmae.harpseal.entity;

import jakarta.persistence.*;
import mangmae.harpseal.entity.type.QuestionType;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue
    @Column(name = "question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToOne
    @JoinColumn(name = "attachment_id")
    private Attachment attachment;

    @OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @Column(name = "multiple_question_choice")
    private List<MultipleQuestionChoice> choices = new ArrayList<>();

    private String content;

    @Enumerated(value = EnumType.STRING)
    private QuestionType questionType;

    private String answer;
}