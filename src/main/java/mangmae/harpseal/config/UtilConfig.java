package mangmae.harpseal.config;

import mangmae.harpseal.util.FilePathUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfig {

    @Bean
    public FilePathUtil filePathUtil() {
        return FilePathUtil.of();
    }

}
