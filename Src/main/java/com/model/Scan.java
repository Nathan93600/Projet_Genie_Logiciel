package com.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;

@Entity
public class Scan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer maxFiles;
    private Integer maxDepth;
    private String fileNameFilter;
    private String fileTypeFilter;
    private LocalDateTime scanDate;
    private Long executionTime;
    private String scanPath;
    

    @OneToMany(mappedBy = "scan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Fichier> fichiers = new HashSet<>();

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
