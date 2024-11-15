package com.velocity.features.gestionUsuarios;

import com.velocity.config.ConfigManager;

public class GestionRoles {
    private ConfigManager configManager;

    public GestionRoles(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void register() {
        if (configManager.isFeatureEnabled("gestionUsuarios.GestionRoles")) {
            System.out.println("GestionRoles está habilitado.");
            // Implementación de la lógica
        } else {
            System.out.println("GestionRoles está deshabilitado.");
        }
    }
}
