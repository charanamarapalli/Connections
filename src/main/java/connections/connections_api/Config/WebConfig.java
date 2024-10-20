package connections.connections_api.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // Adjust this path according to your API endpoints
                .allowedOrigins("http://localhost:3000")  // Allow requests from your React app
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow methods that are being used
                .allowedHeaders("*")
                .allowCredentials(true) // If you want to allow cookies
                .maxAge(3600); // 1 hour duration for preflight
    }
}
