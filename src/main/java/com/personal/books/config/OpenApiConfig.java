package com.personal.books.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Books API",
        description = "API for managing personal book collection",
        version = "1.0.0"
    ),
    servers = {
        @Server(url = "http://localhost:8080", description = "Local development server")
    }
)
public class OpenApiConfig {
}