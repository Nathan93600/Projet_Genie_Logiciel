package com.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;

/**
 * Représente une entité de balayage.
 */
@Entity
public class Scan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer maxFiles; // Nombre maximal de fichiers
    private Integer maxDepth; // Profondeur maximale
    private String fileNameFilter; // Filtre de nom de fichier
    private String fileTypeFilter; // Filtre de type de fichier
    private LocalDateTime scanDate; // Date de balayage
    private Long executionTime; // Temps d'exécution
    private String scanPath; // Chemin de balayage
    
    @OneToMany(mappedBy = "scan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Fichier> fichiers = new HashSet<>(); // Ensemble de fichiers scannés associés à ce balayage

    // Getters and Setters

    /**
     * Renvoie l'ID du balayage.
     * @return L'ID du balayage
     */
    public Long getId() { return id; }

    /**
     * Définit l'ID du balayage.
     * @param id L'ID du balayage à définir
     */
    public void setId(Long id) { this.id = id; }

    // Autres getters et setters pour les propriétés maxFiles, maxDepth, fileNameFilter, fileTypeFilter, scanDate, executionTime, scanPath

    /**
     * Renvoie la collection de fichiers scannés associés à ce balayage.
     * @return La collection de fichiers scannés
     */
    public Set<Fichier> getFichiers() { return fichiers; }

    /**
     * Définit la collection de fichiers scannés associés à ce balayage.
     * @param fichiers La collection de fichiers scannés à définir
     */
    public void setFichiers(Set<Fichier> fichiers) { this.fichiers = fichiers; }

    /**
     * Calcule le temps total d'exécution pour tous les fichiers scannés associés à ce balayage.
     * @return Le temps total d'exécution
     */
    public double getTempsExecutionTotal() {
        if (fichiers == null || fichiers.isEmpty()) {
            return 0;
        }
        double total = fichiers.stream()
                               .mapToDouble(fichier -> fichier.getExecutionTime() != null ? fichier.getExecutionTime() : 0)
                               .sum();
        return total;
    }

    /**
     * Définit le chemin de balayage.
     * @param scanPath Le chemin de balayage à définir
     */
    public void setScanPath(String scanPath) { this.scanPath = scanPath; }

    /**
     * Renvoie le chemin de balayage.
     * @return Le chemin de balayage
     */
    public String getScanPath() { return this.scanPath; }
}
