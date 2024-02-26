package com.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entité représentant un fichier scanné.
 */
@Entity
public class Fichier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String type;
    private Long poids;
    private LocalDateTime dateModification;
    private String repertoire;
    private Long executionTime;

    @ManyToOne
    @JoinColumn(name = "scan_id")
    private Scan scan;

    // Getters and Setters

    /**
     * Renvoie l'identifiant du fichier.
     * @return L'identifiant du fichier
     */
    public Long getId() { return id; }

    /**
     * Définit l'identifiant du fichier.
     * @param id L'identifiant du fichier
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Renvoie le nom du fichier.
     * @return Le nom du fichier
     */
    public String getNom() { return nom; }

    /**
     * Définit le nom du fichier.
     * @param nom Le nom du fichier
     */
    public void setNom(String nom) { this.nom = nom; }

    /**
     * Renvoie le type du fichier.
     * @return Le type du fichier
     */
    public String getType() { return type; }

    /**
     * Définit le type du fichier.
     * @param type Le type du fichier
     */
    public void setType(String type) { this.type = type; }

    /**
     * Renvoie le poids du fichier.
     * @return Le poids du fichier
     */
    public Long getPoids() { return poids; }

    /**
     * Définit le poids du fichier.
     * @param poids Le poids du fichier
     */
    public void setPoids(Long poids) { this.poids = poids; }

    /**
     * Renvoie la date de modification du fichier.
     * @return La date de modification du fichier
     */
    public LocalDateTime getDateModification() { return dateModification; }

    /**
     * Définit la date de modification du fichier.
     * @param dateModification La date de modification du fichier
     */
    public void setDateModification(LocalDateTime dateModification) { this.dateModification = dateModification; }

    /**
     * Renvoie le répertoire du fichier.
     * @return Le répertoire du fichier
     */
    public String getRepertoire() { return repertoire; }

    /**
     * Définit le répertoire du fichier.
     * @param repertoire Le répertoire du fichier
     */
    public void setRepertoire(String repertoire) { this.repertoire = repertoire; }

    /**
     * Renvoie le scan auquel appartient le fichier.
     * @return Le scan auquel appartient le fichier
     */
    public Scan getScan() { return scan; }

    /**
     * Définit le scan auquel appartient le fichier.
     * @param scan Le scan auquel appartient le fichier
     */
    public void setScan(Scan scan) { this.scan = scan; }

    /**
     * Définit le temps d'exécution de l'analyse du fichier.
     * @param executionTime Le temps d'exécution de l'analyse du fichier
     */
    public void setExecutionTime(Long executionTime) { this.executionTime = executionTime; }

    /**
     * Renvoie le temps d'exécution de l'analyse du fichier.
     * @return Le temps d'exécution de l'analyse du fichier
     */
    public Long getExecutionTime() { return executionTime; }
}
