package com.model.state;

public interface EtatScan {
    void demarrerScan(ScanContext context);
    void terminerScan(ScanContext context);
    void erreurScan(ScanContext context);
}

