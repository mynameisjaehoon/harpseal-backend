package mangmae.harpseal;

import mangmae.harpseal.global.util.UserUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
public class HarpsealApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarpsealApplication.class, args);
	}

	@Bean
	public AuditorAware<String> auditorProvider() {
		// TODO: 2023/12/22 추후 만들어질 유저이름 모듈로 변경
		return () -> Optional.of(UserUtil.makeRandomUsername());
	}

}
