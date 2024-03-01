package com.model.state;

public class PretPourScan implements EtatScan {
    @Override
    public void demarrerScan(ScanContext context) {
        System.out.println("Scan démarré");
        context.setEtatScan(new EnCoursDeScan());
    }

    @Override
    public void terminerScan(ScanContext context) {
        System.out.println("Le scan ne peut pas être terminé si non démarré");
    }

    @Override
    public void erreurScan(ScanContext context) {
        System.out.println("Erreur avant démarrage");
    }
}



