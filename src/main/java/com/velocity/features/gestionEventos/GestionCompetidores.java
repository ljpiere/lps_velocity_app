package com.velocity.features.gestionEventos;

import com.velocity.config.ConfigManager;

public class GestionCompetidores {
    private ConfigManager configManager;

    public GestionCompetidores(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void register() {
        if (configManager.isFeatureEnabled("gestionEventos.GestionCompetidores")) {
            System.out.println("GestionCompetidores está habilitado.");
            // Implementación de la lógica
        } else {
            System.out.println("GestionCompetidores está deshabilitado.");
        }
    }
}
