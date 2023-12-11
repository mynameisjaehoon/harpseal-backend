package mangmae.harpseal.util;

import mangmae.harpseal.exception.PasswordNotMatchException;

public class SecurityUtil {

    private SecurityUtil() {
    }

    public static void verifyPassword(String password1, String password2) {
        if (!password1.equals(password2)) {
            throw new PasswordNotMatchException(password1, password2);
        }
    }

}
