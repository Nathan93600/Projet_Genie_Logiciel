package com.repository;

import com.model.Fichier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface de repository pour l'entité Fichier.
 * Étend JpaRepository pour fournir des opérations de base de données pour l'entité Fichier.
 */

@Repository
public interface FichierRepository extends JpaRepository<Fichier, Long> {
    /**
     * Recherche des fichiers dont le nom contient une chaîne spécifique.
     * @param nom La chaîne à rechercher dans le nom des fichiers.
     * @return Liste de fichiers correspondant au critère de recherche.
     */
    List<Fichier> findByNomContaining(String nom);

    /**
     * Recherche des fichiers par type.
     * @param typeFichier Le type de fichier à rechercher.
     * @return Liste de fichiers du type spécifié.
     */
    List<Fichier> findByTypeFichier(String typeFichier);

    /**
     * Recherche des fichiers modifiés entre deux dates.
     * @param dateDebut La date de début de la période de recherche.
     * @param dateFin La date de fin de la période de recherche.
     * @return Liste de fichiers modifiés dans l'intervalle spécifié.
     */
    List<Fichier> findByDateModificationBetween(Long dateDebut, Long dateFin);

    /**
     * Recherche des fichiers contenus dans un répertoire spécifique.
     * @param repertoire Le chemin du répertoire à rechercher.
     * @return Liste de fichiers contenus dans le répertoire spécifié.
     */
    List<Fichier> findByRepertoireContenant(String repertoire);
}
