package com.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Fichier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private LocalDateTime dateModification;
    private Long poids;
    private String type;
    private String repertoire;

    @ManyToOne
    @JoinColumn(name = "scan_id")
    private Scan scan;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public LocalDateTime getDateModification() { return dateModification; }
    public void setDateModification(LocalDateTime dateModification) { this.dateModification = dateModification; }
    public Long getPoids() { return poids; }
    public void setPoids(Long poids) { this.poids = poids; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getRepertoire() { return repertoire; }
    public void setRepertoire(String repertoire) { this.repertoire = repertoire; }
    public Scan getScan() { return scan; }
    public void setScan(Scan scan) { this.scan = scan; }
}
