package com.quimbaya.clientes_api.security.infraestructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("Fast reading 4.0").version("1.0").description("Api Rest System Service Desk4.0"))
            .addSecurityItem(new SecurityRequirement().addList("JWT"))
            .components(
                new Components()
                    .addSecuritySchemes("JWT",
                        new SecurityScheme()
                            .name("JWT")
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")));
    }  
}
