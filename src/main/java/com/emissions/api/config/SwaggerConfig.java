package com.emissions.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Análisis de Emisiones de CO₂")
                        .version("1.0.0")
                        .description("API para el análisis de la reducción de emisiones de CO₂ " +
                                "durante la pandemia de COVID-19 en El Salvador")
                        .contact(new Contact()
                                .name("Equipo de Investigación")
                                .email("investigacion@universidad.edu.sv")));
    }
}