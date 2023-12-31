package mangmae.harpseal.global.util;

import lombok.extern.slf4j.Slf4j;
import mangmae.harpseal.global.exception.PasswordNotMatchException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


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

    public static String encryptSha256(final String object) {

        byte[] bytes = object.getBytes();
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(bytes);

            byte[] byteData = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
            }

            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

}
