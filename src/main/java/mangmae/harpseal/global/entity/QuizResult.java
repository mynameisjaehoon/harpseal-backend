package mangmae.harpseal.global.entity;

import jakarta.persistence.*;
import mangmae.harpseal.global.entity.auditing.CreatedDateEntity;

@Entity
public class QuizResult extends CreatedDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    private Integer score;
}
