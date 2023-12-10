package mangmae.harpseal.multipartTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.*;


@RestController
@Slf4j
public class TestController {

    @PostMapping(value = "/api/v1/character", consumes = {APPLICATION_JSON_VALUE, MULTIPART_FORM_DATA_VALUE})
    public void saveCharacter(
            @RequestPart(value = "request") CharacterCreateRequest request,
            @RequestPart(value = "imgFile") MultipartFile imgFile
    ) {
        log.info("이름={}, 나이={}, 이미지={}", request.getName(), request.getAge(), imgFile);

    }
}
