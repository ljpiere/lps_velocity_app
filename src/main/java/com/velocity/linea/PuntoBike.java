package com.velocity.linea;

import com.velocity.config.ConfigManager;

public class PuntoBike {
    private ConfigManager configManager;

    public PuntoBike(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void startFeature() {
        if (configManager.isFeatureEnabled("linea.PuntoBike")) {
            System.out.println("Punto Bike está habilitado y funcionando.");
            // Implementación de la lógica
        } else {
            System.out.println("Punto Bike está deshabilitado.");
        }
    }
}