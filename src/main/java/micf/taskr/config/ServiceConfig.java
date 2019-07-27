package micf.taskr.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "micf.taskr.service.*" })
public class ServiceConfig {
}