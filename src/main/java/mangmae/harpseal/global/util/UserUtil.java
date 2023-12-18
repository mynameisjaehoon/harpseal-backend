package mangmae.harpseal.global.util;

import java.util.UUID;

public class UserUtil {

    private UserUtil(){
    }

    public static String makeRandomUsername() {
        StringBuilder sb = new StringBuilder();
        sb.append("user-");
        sb.append(UUID.randomUUID().toString().substring(0, 8));
        return sb.toString();
    }

}
