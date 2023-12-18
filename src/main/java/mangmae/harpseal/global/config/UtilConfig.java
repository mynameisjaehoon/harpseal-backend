package mangmae.harpseal.global.config;

import mangmae.harpseal.global.util.FileUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfig {

    @Bean
    public FileUtil filePathUtil() {
        return FileUtil.of();
    }

}
