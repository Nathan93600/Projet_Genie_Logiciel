package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.service.ScanContext;

/**
 * Classe principale de l'application Spring Boot.
 * Utilise l'annotation @SpringBootApplication pour marquer la configuration automatique de l'application,
 * la recherche de composants, et pour activer la configuration Spring Boot.
 */
@SpringBootApplication
public class Application {

    /**
     * Le point d'entrée principal de l'application.
     * Cette méthode lance l'application Spring Boot en utilisant SpringApplication.run.
     *
     * @param args Les arguments de ligne de commande passés au démarrage de l'application.
     */
    public static void main(String[] args) {
        ScanContext context = new ScanContext();
        context.demarrerScan(); // Change l'état à EnCoursDeScan
        context.terminerScan(); // Change l'état à ScanTermine
        SpringApplication.run(Application.class, args);
    }
}


