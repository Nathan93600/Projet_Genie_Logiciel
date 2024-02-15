package model;

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
}
