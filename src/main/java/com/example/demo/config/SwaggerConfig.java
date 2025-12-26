package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Demo Application API")
                        .version("1.0")
                        .description("API documentation for Spring Boot application"))
                .servers(List.of(
                        new Server().url("/").description("Current Server"),
                        new Server().url("http://localhost:9195").description("Local Development")
                ));
    }
}