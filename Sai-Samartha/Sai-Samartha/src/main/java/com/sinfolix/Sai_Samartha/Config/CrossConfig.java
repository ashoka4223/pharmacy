package com.sinfolix.Sai_Samartha.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CrossConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Apply to all endpoints
                .allowedOrigins("http://localhost:3000","http://localhost:8080")  // Allow requests from the frontend origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Specify allowed methods
                .allowedHeaders("Authorization", "Content-Type")  // Specify allowed headers
                .exposedHeaders("Authorization")  // Allow frontend access to Authorization header in responses
                .allowCredentials(true);  // Allow cookies and credentials
    }

}
