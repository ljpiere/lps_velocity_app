package com.velocity.linea;

import com.velocity.config.ConfigManager;

public class TrainingHub {
    private ConfigManager configManager;

    public TrainingHub(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void startFeature() {
        if (configManager.isFeatureEnabled("linea.TrainingHub")) {
            System.out.println("TrainingHub está habilitado y funcionando.");
            // Implementación de la lógica
        } else {
            System.out.println("TrainingHub está deshabilitado.");
        }
    }
}