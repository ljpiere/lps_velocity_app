package com.velocity.features.gestionUsuarios;

import com.velocity.config.ConfigManager;

public class GestionPlanes {
    private ConfigManager configManager;

    public GestionPlanes(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void register() {
        if (configManager.isFeatureEnabled("gestionUsuarios.GestionPlanes")) {
            System.out.println("GestionPlanes está habilitado.");
            // Implementación de la lógica
        } else {
            System.out.println("GestionPlanes está deshabilitado.");
        }
    }
}
