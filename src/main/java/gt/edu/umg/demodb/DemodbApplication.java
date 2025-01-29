package gt.edu.umg.demodb;

import java.util.TimeZone;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import gt.edu.umg.demodb.utils.AppProperty;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableConfigurationProperties({ AppProperty.class })
public class DemodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemodbApplication.class, args);
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DemodbApplication.class);
	}

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Guatemala"));
	}
	
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*")
				.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
				.allowedHeaders("authorization", "content-type", "x-auth-token")
				.exposedHeaders("x-auth-token");
			}
		};
	}

}
