package model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

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
    
    @OneToMany(mappedBy = "scan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Fichier> fichiers;

    // Getters and Setters
}
