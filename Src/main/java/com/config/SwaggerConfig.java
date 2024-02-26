package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.PathSelectors;

/**
 * Classe de configuration pour Swagger.
 */
@Configuration
@EnableSwagger2 // Active Swagger 2 dans l'application
public class SwaggerConfig {

    /**
     * Configure et crée un bean Docket pour la documentation Swagger.
     * @return Docket - Le bean Docket configuré
     */
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2) // Spécifie la version Swagger 2
          .select()                                  
          .apis(RequestHandlerSelectors.any()) // Sélectionne toutes les classes de contrôleur pour la documentation de l'API
          .paths(PathSelectors.any()) // Inclut tous les chemins dans la documentation de l'API
          .build(); // Construit le Docket
    }
}
