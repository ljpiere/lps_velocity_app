package com.velocity.features.gestionUsuarios;

import com.velocity.config.ConfigManager;

public class GestionMembresia {
    private ConfigManager configManager;

    public GestionMembresia(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void register() {
        if (configManager.isFeatureEnabled("gestionUsuarios.GestionMembresia")) {
            System.out.println("GestionMembresia está habilitado.");
            // Implementación de la lógica
        } else {
            System.out.println("GestionMembresia está deshabilitado.");
        }
    }
}
