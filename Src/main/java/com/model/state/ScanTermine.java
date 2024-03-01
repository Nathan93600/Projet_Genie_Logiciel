package com.model.state;

public class ScanTermine implements EtatScan {
    @Override
    public void demarrerScan(ScanContext context) {
        System.out.println("Le scan est déjà terminé. Impossible de redémarrer.");
    }

    @Override
    public void terminerScan(ScanContext context) {
        System.out.println("Le scan est déjà terminé.");
    }

    @Override
    public void erreurScan(ScanContext context) {
        System.out.println("Le scan est terminé, aucune erreur ne peut survenir.");
    }
}
