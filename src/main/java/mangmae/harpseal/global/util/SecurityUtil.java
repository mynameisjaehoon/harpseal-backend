package mangmae.harpseal.global.util;

import lombok.extern.slf4j.Slf4j;
import mangmae.harpseal.global.exception.PasswordNotMatchException;


@Slf4j
public class SecurityUtil {

    private SecurityUtil() {
    }

    public static void verifyPassword(String password1, String password2) {
        log.info("password1={}, password2={}", password1, password2);
        if (!password1.equals(password2)) {
            throw new PasswordNotMatchException("password is invalid");
        }
    }

}
