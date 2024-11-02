package com.velocity.features.gestionBicicletas;

import com.velocity.config.ConfigManager;

public class SistemaAlquiler {
    private ConfigManager configManager;

    public SistemaAlquiler(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void register() {
        if (configManager.isFeatureEnabled("gestionBicicletas.SistemaAlquiler")) {
            System.out.println("SistemaAlquiler está habilitado.");
            // Implementación de la lógica
        } else {
            System.out.println("SistemaAlquiler está deshabilitado.");
        }
    }
}
