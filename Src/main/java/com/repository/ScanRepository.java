package com.repository;

import com.model.Scan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScanRepository extends JpaRepository<Scan, Long> {
}
