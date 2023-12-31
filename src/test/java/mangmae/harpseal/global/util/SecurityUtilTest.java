package mangmae.harpseal.global.util;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
class SecurityUtilTest {

    @Test
    @DisplayName("SHA-256 암호화 테스트")
    void encryptString() {
        String password = "1234";
        String firstTry = SecurityUtil.encryptSha256(password);
        String secondTry = SecurityUtil.encryptSha256(password);

        log.info("encrypt result=[{}]", firstTry);
        assertThat(firstTry).isEqualTo(secondTry);

    }

}