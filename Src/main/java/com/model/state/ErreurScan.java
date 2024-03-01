package com.model.state;

public class ErreurScan implements EtatScan {
    @Override
    public void demarrerScan(ScanContext context) {
        System.out.println("Le scan est en erreur. Corrigez l'erreur avant de redémarrer.");
    }

    @Override
    public void terminerScan(ScanContext context) {
        System.out.println("Corrigez l'erreur avant de terminer le scan.");
    }

    @Override
    public void erreurScan(ScanContext context) {
        System.out.println("Le scan est déjà en état d'erreur.");
    }
}