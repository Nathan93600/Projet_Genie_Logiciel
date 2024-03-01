package com.service;
import com.model.state.EtatScan;

public class ScanContext {
    private EtatScan etatScan;

    public ScanContext() {
        etatScan = new PretPourScan(); // État initial
    }

    public void setEtatScan(EtatScan etatScan) {
        this.etatScan = etatScan;
    }

    public void demarrerScan() {
        etatScan.demarrerScan(this);
    }

    public void terminerScan() {
        etatScan.terminerScan(this);
    }

    public void erreurScan() {
        etatScan.erreurScan(this);
    }
}

