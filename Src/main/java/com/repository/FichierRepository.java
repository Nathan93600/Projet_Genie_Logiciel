package com.repository;

import com.model.Fichier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichierRepository extends JpaRepository<Fichier, Long> {
}
