package mangmae.harpseal.global.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.global.entity.type.AttachmentType;

import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Enumerated(EnumType.STRING)
    private AttachmentType attachmentType;
    private String filePath;

    public void changeQuestion(Question question) {
        this.question = question;
    }

    public Attachment(AttachmentType type, String filePath) {
        this.attachmentType = type;
        this.filePath = filePath;
    }



}
