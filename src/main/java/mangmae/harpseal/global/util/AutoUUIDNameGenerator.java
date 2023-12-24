package mangmae.harpseal.global.util;


import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AutoUUIDNameGenerator implements AutoNameGenerator {
    @Override
    public String make() {
        StringBuilder sb = new StringBuilder();
        sb.append("user-");
        sb.append(UUID.randomUUID().toString().substring(0, 8));
        return sb.toString();
    }
}
