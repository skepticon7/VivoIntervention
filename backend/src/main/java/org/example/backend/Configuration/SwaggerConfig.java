package org.example.backend.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Your API Title",
                version = "1.0",
                description = "API Documentation"
        )
)
public class SwaggerConfig {
}