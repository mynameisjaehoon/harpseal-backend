package mangmae.harpseal.entity;

import jakarta.persistence.*;
import mangmae.harpseal.entity.auditing.CreatedDateEntity;

@Entity
public class QuizResult extends CreatedDateEntity {

    @Id
    @GeneratedValue
    @Column(name = "result_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    private Integer score;
}
