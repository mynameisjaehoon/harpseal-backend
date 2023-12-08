package mangmae.harpseal.entity;

import jakarta.persistence.*;
import mangmae.harpseal.entity.auditing.CreatedInfoEntity;

@Entity
public class Comment extends CreatedInfoEntity {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String content;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
}