package com.repository;

import com.model.Scan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface de repository pour l'entité Scan.
 * Étend JpaRepository pour fournir des opérations de base de données pour l'entité Scan.
 */
@Repository
public interface ScanRepository extends JpaRepository<Scan, Long> {
    /**
     * Recherche des scans ayant un nombre maximal de fichiers.
     * @param nombreMaxFichiers Le nombre maximal de fichiers pour les scans à rechercher.
     * @return Liste de scans correspondant au critère de recherche.
     */
    List<Scan> findByNombreMaxFichiers(int nombreMaxFichiers);
    /**
     * Recherche des scans avec une profondeur d'arborescence maximale spécifiée.
     * @param profondeurMaxArborescence La profondeur maximale d'arborescence pour les scans à rechercher.
     * @return Liste de scans correspondant au critère de recherche.
     */
    List<Scan> findByProfondeurMaxArborescence(int profondeurMaxArborescence);
    /**
     * Recherche des scans effectués entre deux dates.
     * @param dateDebut La date de début de la période de recherche.
     * @param dateFin La date de fin de la période de recherche.
     * @return Liste de scans réalisés dans l'intervalle spécifié.
     */
    List<Scan> findByDateScanBetween(Long dateDebut, Long dateFin);

    /**
     * Recherche des scans dont le temps d'exécution est supérieur ou égal à une valeur minimale.
     * @param tempsExecutionMin Le temps d'exécution minimal pour les scans à rechercher.
     * @return Liste de scans dont le temps d'exécution satisfait au critère de recherche.
     */
    List<Scan> findByTempsExecutionScanGreaterThanEqual(Long tempsExecutionMin);
}
