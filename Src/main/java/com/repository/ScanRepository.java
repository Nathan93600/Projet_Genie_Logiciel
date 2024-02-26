package com.repository;

import com.model.Scan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface de repository pour l'entité Scan, permettant d'effectuer des opérations CRUD sur la base de données.
 */
@Repository
public interface ScanRepository extends JpaRepository<Scan, Long> {

    /**
     * Recherche les scans ayant un nombre maximal de fichiers spécifié.
     * @param nombreMaxFichiers Le nombre maximal de fichiers à rechercher
     * @return Une liste de scans ayant le nombre maximal de fichiers spécifié
     */
    List<Scan> findByNombreMaxFichiers(int nombreMaxFichiers);

    /**
     * Recherche les scans ayant une profondeur maximale d'arborescence spécifiée.
     * @param profondeurMaxArborescence La profondeur maximale d'arborescence à rechercher
     * @return Une liste de scans ayant la profondeur maximale d'arborescence spécifiée
     */
    List<Scan> findByProfondeurMaxArborescence(int profondeurMaxArborescence);

    /**
     * Recherche les scans dont la date de scan est comprise entre deux dates spécifiées.
     * @param dateDebut La date de début de la période de recherche
     * @param dateFin La date de fin de la période de recherche
     * @return Une liste de scans dont la date de scan est comprise entre les dates spécifiées
     */
    List<Scan> findByDateScanBetween(Long dateDebut, Long dateFin);

    /**
     * Recherche les scans ayant un temps d'exécution de scan supérieur ou égal à une valeur minimale spécifiée.
     * @param tempsExecutionMin Le temps d'exécution minimum à rechercher
     * @return Une liste de scans ayant un temps d'exécution de scan supérieur ou égal à la valeur minimale spécifiée
     */
    List<Scan> findByTempsExecutionScanGreaterThanEqual(Long tempsExecutionMin);
}
