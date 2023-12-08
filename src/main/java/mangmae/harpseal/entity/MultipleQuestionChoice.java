package mangmae.harpseal.entity;

import jakarta.persistence.*;

@Entity
public class MultipleQuestionChoice {
    @Id
    @GeneratedValue
    @Column(name = "choice_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    private String content;
}
