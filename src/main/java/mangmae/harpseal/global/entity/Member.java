package mangmae.harpseal.global.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mangmae.harpseal.global.entity.type.MemberType;
import org.springframework.context.annotation.Configuration;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String password;

    @Enumerated(value = EnumType.STRING)
    private MemberType type;

}
