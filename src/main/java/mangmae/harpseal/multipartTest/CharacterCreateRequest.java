package mangmae.harpseal.multipartTest;

import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@ToString
public class CharacterCreateRequest {
    private String name;
    private Long age;
}
