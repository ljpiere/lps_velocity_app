package com.velocity.features.gestionUsuarios;

import com.velocity.config.ConfigManager;

public class PerfilesUsuario {
    private ConfigManager configManager;

    public PerfilesUsuario(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void register() {
        if (configManager.isFeatureEnabled("gestionUsuarios.PerfilesUsuario")) {
            System.out.println("PerfilesUsuario está habilitado.");
            // Implementación de la lógica
        } else {
            System.out.println("PerfilesUsuario está deshabilitado.");
        }
    }
}
