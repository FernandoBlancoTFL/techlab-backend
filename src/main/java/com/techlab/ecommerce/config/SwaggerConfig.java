package com.techlab.ecommerce.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI techlabOpenAPI() {

        return new OpenAPI()

                .info(new Info()

                        .title("TechLab E-Commerce API")

                        .description("""
                                REST API developed with Spring Boot for the
                                TechLab Final Backend Project.
                                """)

                        .version("1.0.0"))

                .externalDocs(new ExternalDocumentation()

                        .description("Project Documentation")

                        .url("https://github.com/FernandoBlancoTFL/techlab-backend"));
    }

}