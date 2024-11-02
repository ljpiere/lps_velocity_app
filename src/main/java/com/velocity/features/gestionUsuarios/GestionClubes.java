package com.velocity.features.gestionUsuarios;

import com.velocity.config.ConfigManager;

public class GestionClubes {
    private ConfigManager configManager;

    public GestionClubes(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void register() {
        if (configManager.isFeatureEnabled("gestionUsuarios.GestionClubes")) {
            System.out.println("GestionClubes está habilitado.");
            // Implementación de la lógica
        } else {
            System.out.println("GestionClubes está deshabilitado.");
        }
    }
}
