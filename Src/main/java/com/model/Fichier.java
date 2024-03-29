package com.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Classe entité représentant un fichier dans le système.
 * Annotée avec @Entity pour indiquer qu'il s'agit d'une entité JPA.
 */
@Entity
public class Fichier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique du fichier.
    private String nom; // Nom du fichier.
    private String type; // Type du fichier (extension).
    private Long poids; // Poids du fichier en octets.
    private LocalDateTime dateModification; // Date de dernière modification du fichier.
    private String repertoire; // Chemin du répertoire contenant le fichier.
    private Long executionTime; // Temps d'exécution associé au fichier.

    @ManyToOne
    @JoinColumn(name = "scan_id")
    private Scan scan; // Référence au scan associé à ce fichier.

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Long getPoids() { return poids; }
    public void setPoids(Long poids) { this.poids = poids; }
    public LocalDateTime getDateModification() { return dateModification; }
    public void setDateModification(LocalDateTime dateModification) { this.dateModification = dateModification; }
    public String getRepertoire() { return repertoire; }
    public void setRepertoire(String repertoire) { this.repertoire = repertoire; }
    public Scan getScan() { return scan; }
    public void setScan(Scan scan) { this.scan = scan; }
    public void setExecutionTime(Long executionTime) { this.executionTime = executionTime; } // Setter pour le temps d'exécution
    public Long getExecutionTime() { return executionTime; }
}
