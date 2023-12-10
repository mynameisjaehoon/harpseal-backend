package mangmae.harpseal.domain.quiz.util;

public class SecurityUtil {

    private SecurityUtil() {
    }

    public static boolean isPasswordEqual(String password1, String password2) {
        return password1.equals(password2);
    }
}
