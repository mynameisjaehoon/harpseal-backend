package mangmae.harpseal.global.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mangmae.harpseal.global.entity.auditing.CreatedInfoEntity;

@Entity
@Table(name = "comments") // `comment`는 예약어 이기 때문에 comments로 함
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends CreatedInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;
    private String password;
    private Integer likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    public Comment(String content, String password) {
        this.content = content;
        this.password = password;
        this.likeCount = 0;
    }
}
