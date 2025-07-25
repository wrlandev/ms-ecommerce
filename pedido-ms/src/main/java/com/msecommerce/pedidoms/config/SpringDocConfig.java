package com.msecommerce.pedidoms.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pedido-MS API")

                        .version("v1")

                        .description("API do Microsserviço de orquestração e gerenciamento de Pedidos.")

                        .termsOfService("http://swagger.io/terms/")

                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}