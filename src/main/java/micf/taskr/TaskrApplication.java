package micf.taskr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = "micf.taskr")
public class TaskrApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(TaskrApplication.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	  registry
		  .addResourceHandler("/test/**")
		  .addResourceLocations("file:///C:/tmp/")
		  .addResourceLocations("file:////tmp/")
		  .setCachePeriod(0);
	}
  
	@Override
	public void addCorsMappings(CorsRegistry registry) {
	  registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
		  .allowedHeaders("*");
	}
}
