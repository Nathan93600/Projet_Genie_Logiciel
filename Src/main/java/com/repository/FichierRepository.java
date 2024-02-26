package com.repository;

import com.model.Fichier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface de repository pour l'entité Fichier, permettant d'effectuer des opérations CRUD sur la base de données.
 */
@Repository
public interface FichierRepository extends JpaRepository<Fichier, Long> {

    /**
     * Recherche les fichiers dont le nom contient la chaîne spécifiée.
     * @param nom La chaîne à rechercher dans le nom des fichiers
     * @return Une liste de fichiers correspondant au critère de recherche
     */
    List<Fichier> findByNomContaining(String nom);

    /**
     * Recherche les fichiers ayant le type de fichier spécifié.
     * @param typeFichier Le type de fichier à rechercher
     * @return Une liste de fichiers ayant le type de fichier spécifié
     */
    List<Fichier> findByTypeFichier(String typeFichier);

    /**
     * Recherche les fichiers dont la date de modification est comprise entre deux dates spécifiées.
     * @param dateDebut La date de début de la période de recherche
     * @param dateFin La date de fin de la période de recherche
     * @return Une liste de fichiers dont la date de modification est comprise entre les dates spécifiées
     */
    List<Fichier> findByDateModificationBetween(Long dateDebut, Long dateFin);

    /**
     * Recherche les fichiers dont le répertoire contient la chaîne spécifiée.
     * @param repertoire La chaîne à rechercher dans les répertoires des fichiers
     * @return Une liste de fichiers dont le répertoire contient la chaîne spécifiée
     */
    List<Fichier> findByRepertoireContenant(String repertoire);
}
