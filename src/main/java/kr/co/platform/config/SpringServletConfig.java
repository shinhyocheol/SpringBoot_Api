package kr.co.platform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value = {"classpath*:spring/spring-servlet.xml"})
public class SpringServletConfig {
	
}
