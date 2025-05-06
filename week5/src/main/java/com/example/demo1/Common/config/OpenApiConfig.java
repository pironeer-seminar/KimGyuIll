package com.example.demo1.Common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Pironeer API 명세서",
                description = "API 명세서",
                version = "v1",
                contact = @Contact(
                        name = "김규일",
                        email = "rlarbdlf222@naver.com"
                )
        )
)
@Configuration
public class OpenApiConfig {
}