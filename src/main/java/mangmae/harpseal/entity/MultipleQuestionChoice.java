package mangmae.harpseal.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MultipleQuestionChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "choice_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    private int number;
    private String content;

    public MultipleQuestionChoice(String content) {
        this.content = content;
    }

    public MultipleQuestionChoice(int number, String content) {
        this.number = number;
        this.content = content;
    }

    /**
     * 연관관계에 있는 `Question`을 추가하는 메서드이다.<br>
     * 이메서드를 호출했을 때 `Question`에도 `Choice` 엔티티 목록이 추가되지는 않는다는 점에 주의해야한다.<br>
     * `Question`에서만 호출되는 메서드이다.<br>
     * @param question 연관관계에 있는 Question Entity
     */
    public void changeQuestion(Question question) {
        this.question = question;
    }
}
