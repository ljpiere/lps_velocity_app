package com.velocity.features.gestionUsuarios;

import com.velocity.config.ConfigManager;

public class RegistroCiclistas {
    private ConfigManager configManager;

    public RegistroCiclistas(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void register() {
        if (configManager.isFeatureEnabled("gestionUsuarios.RegistroCiclistas")) {
            System.out.println("Registro de ciclistas está habilitado.");
            // Implementación de la lógica
        } else {
            System.out.println("Registro de ciclistas está deshabilitado.");
        }
    }
}
