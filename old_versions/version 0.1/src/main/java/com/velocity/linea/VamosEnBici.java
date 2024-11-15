package com.velocity.linea;

import com.velocity.config.ConfigManager;

public class VamosEnBici {
    private ConfigManager configManager;

    public VamosEnBici(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void startFeature() {
        if (configManager.isFeatureEnabled("linea.VamosEnBici")) {
            System.out.println("Vamos en Bici está habilitado y funcionando.");
            // Implementación de la lógica
        } else {
            System.out.println("Vamos en Bici está deshabilitado.");
        }
    }
}
