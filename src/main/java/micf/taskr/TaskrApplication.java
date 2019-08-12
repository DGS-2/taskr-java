package micf.taskr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = "micf.taskr")
public class TaskrApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(TaskrApplication.class, args);
	}
    
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(11);
    }
}
