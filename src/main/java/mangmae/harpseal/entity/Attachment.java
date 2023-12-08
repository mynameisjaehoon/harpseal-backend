package mangmae.harpseal.entity;

import jakarta.persistence.*;
import mangmae.harpseal.entity.type.AttachmentType;

@Entity
public class Attachment {
    @Id
    @GeneratedValue
    @Column(name = "attachment_id")
    private Long id;

    @OneToOne(mappedBy = "attachment")
    private Question question;

    @Enumerated(EnumType.STRING)
    private AttachmentType attachmentType;

    @Lob
    private String Data;
}
