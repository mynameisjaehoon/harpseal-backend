package mangmae.harpseal.domain.quiz.exception.dto;


import jakarta.persistence.NamedAttributeNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvalidQuizInfoResponse {
    private String message;
}
