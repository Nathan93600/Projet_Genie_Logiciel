package com.repository;

import com.model.Scan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScanRepository extends JpaRepository<Scan, Long> {
    List<Scan> findByNombreMaxFichiers(int nombreMaxFichiers);
    List<Scan> findByProfondeurMaxArborescence(int profondeurMaxArborescence);
    List<Scan> findByDateScanBetween(Long dateDebut, Long dateFin);
    List<Scan> findByTempsExecutionScanGreaterThanEqual(Long tempsExecutionMin);
}
