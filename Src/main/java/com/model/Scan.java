package com.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;

/**
 * Classe entité représentant un scan de fichiers.
 * Annotée avec @Entity pour indiquer qu'il s'agit d'une entité JPA.
 */

@Entity
public class Scan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique du scan.
    private Integer maxFiles; // Nombre maximum de fichiers à scanner.
    private Integer maxDepth; // Profondeur maximum de scan dans l'arborescence des dossiers.
    private String fileNameFilter; // Filtre sur les noms de fichiers à inclure dans le scan.
    private String fileTypeFilter; // Filtre sur les types de fichiers à inclure dans le scan.
    private LocalDateTime scanDate; // Date à laquelle le scan a été effectué.
    private Long executionTime; // Temps d'exécution du scan.
    private String scanPath; // Chemin du répertoire de départ du scan.
    

    @OneToMany(mappedBy = "scan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Fichier> fichiers = new HashSet<>(); // Ensemble des fichiers scannés associés à ce scan.

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getMaxFiles() { return maxFiles; }
    public void setMaxFiles(Integer maxFiles) { this.maxFiles = maxFiles; }
    public Integer getMaxDepth() { return maxDepth; }
    public void setMaxDepth(Integer maxDepth) { this.maxDepth = maxDepth; }
    public String getFileNameFilter() { return fileNameFilter; }
    public void setFileNameFilter(String fileNameFilter) { this.fileNameFilter = fileNameFilter; }
    public String getFileTypeFilter() { return fileTypeFilter; }
    public void setFileTypeFilter(String fileTypeFilter) { this.fileTypeFilter = fileTypeFilter; }
    public LocalDateTime getScanDate() { return scanDate; }
    public void setScanDate(LocalDateTime scanDate) { this.scanDate = scanDate; }
    public Long getExecutionTime() { return executionTime; }
    public void setExecutionTime(Long executionTime) { this.executionTime = executionTime; }
    public Set<Fichier> getFichiers() { return fichiers; }
    public void setFichiers(Set<Fichier> fichiers) {this.fichiers = fichiers;}
    /**
     * Calcule le temps d'exécution total des fichiers associés au scan.
     * @return Le temps d'exécution total en millisecondes.
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
    public void setScanPath(String scanPath) {
        this.scanPath = scanPath;
    }
   
   


    public String getScanPath() {
        return this.scanPath;
    }
}
