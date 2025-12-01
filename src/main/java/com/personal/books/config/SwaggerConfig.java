package com.personal.books.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Books API")
                        .version("1.0")
                        .description("Personal Books Collection API"))
                .servers(List.of(
                        new Server().url("http://books-api-env.eba-wt539zrz.eu-central-1.elasticbeanstalk.com").description("Production server"),
                        new Server().url("http://localhost:5000").description("Local server")
                ));
    }
}