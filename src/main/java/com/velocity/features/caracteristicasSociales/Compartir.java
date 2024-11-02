package com.velocity.features.caracteristicasSociales;

import com.velocity.config.ConfigManager;

public class Compartir {
    private ConfigManager configManager;

    public Compartir(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void register() {
        if (configManager.isFeatureEnabled("caracteristicasSociales.Compartir")) {
            System.out.println("Compartir está habilitado.");
            // Implementación de la lógica
        } else {
            System.out.println("Compartir está deshabilitado.");
        }
    }
}
