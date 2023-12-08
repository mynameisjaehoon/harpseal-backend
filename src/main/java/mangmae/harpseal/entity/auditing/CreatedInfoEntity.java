package mangmae.harpseal.entity.auditing;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class CreatedInfoEntity extends CreatedDateEntity {
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
}
