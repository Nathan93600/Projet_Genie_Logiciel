package com.repository;

import com.model.Fichier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FichierRepository extends JpaRepository<Fichier, Long> {
    List<Fichier> findByNomContaining(String nom);
    List<Fichier> findByTypeFichier(String typeFichier);
    List<Fichier> findByDateModificationBetween(Long dateDebut, Long dateFin);
    List<Fichier> findByRepertoireContenant(String repertoire);
}
