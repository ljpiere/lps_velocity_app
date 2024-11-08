package com.velocity.features.gestionEventos;

import com.velocity.config.ConfigManager;

public class CreacionEventos {
    private ConfigManager configManager;

    public CreacionEventos(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void register() {
        if (configManager.isFeatureEnabled("gestionEventos.RedSocial")) {
            System.out.println("RedSocial está habilitado.");
            // Implementación de la lógica
        } else {
            System.out.println("RedSocial está deshabilitado.");
        }
    }
}
