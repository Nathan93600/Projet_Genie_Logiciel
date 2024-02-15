package service;

import model.Scan;
import model.Fichier;
import org.springframework.stereotype.Service;
import repository.ScanRepository;
import repository.FichierRepository;

@Service
public class ScanService {
    private final ScanRepository scanRepository;
    private final FichierRepository fichierRepository;

    // Constructor injection is recommended
    public ScanService(ScanRepository scanRepository, FichierRepository fichierRepository) {
        this.scanRepository = scanRepository;
        this.fichierRepository = fichierRepository;
    }

    // Methods for scanning, saving, duplicating, etc.
}
