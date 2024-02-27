package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.PathSelectors;

// Importations nécessaires pour la configuration Swagger
/**
 * Classe de configuration pour Swagger 2.
 * Cette classe configure Swagger pour générer automatiquement la documentation de l'API REST de l'application.
 * Swagger offre une interface utilisateur interactive pour explorer et tester les endpoints de l'API.
 */
@Configuration // Indique que cette classe est une classe de configuration Spring
@EnableSwagger2 // Active Swagger 2 dans l'application
public class SwaggerConfig {
    /**
     * Configure et crée un bean Docket pour la documentation de l'API.
     * Le bean Docket est configuré pour inclure tous les endpoints de l'API.
     *
     * @return Un bean Docket configuré pour la documentation Swagger.
     */
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2) // Crée une instance Docket pour la documentation de type Swagger 2
          .select() // Initialise un sélecteur d'API pour la configuration fine de Swagger
          .apis(RequestHandlerSelectors.any()) // Sélectionne tous les contrôleurs pour inclure dans la documentation
          .paths(PathSelectors.any()) // Sélectionne tous les chemins d'API pour inclure dans la documentation
          .build(); // Construit l'instance Docket avec les configurations spécifiées
    }
}
