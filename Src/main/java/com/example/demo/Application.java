package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale pour démarrer l'application Spring Boot.
 */
@SpringBootApplication
public class Application {

    /**
     * Méthode principale pour démarrer l'application Spring Boot.
     * @param args Arguments de la ligne de commande
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
