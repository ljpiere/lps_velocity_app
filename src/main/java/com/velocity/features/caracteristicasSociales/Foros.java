package com.velocity.features.caracteristicasSociales;

import com.velocity.config.ConfigManager;

public class Foros {
    private ConfigManager configManager;

    public Foros(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void register() {
        if (configManager.isFeatureEnabled("caracteristicasSociales.Foros")) {
            System.out.println("Foros está habilitado.");
            // Implementación de la lógica
        } else {
            System.out.println("Foros está deshabilitado.");
        }
    }
}
