package com.velocity.features.gestionEventos;

import com.velocity.config.ConfigManager;

public class PlanificacionRutas {
    private ConfigManager configManager;

    public PlanificacionRutas(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void register() {
        if (configManager.isFeatureEnabled("gestionEventos.PlanificacionRutas")) {
            System.out.println("PlanificacionRutas está habilitado.");
            // Implementación de la lógica
        } else {
            System.out.println("PlanificacionRutas está deshabilitado.");
        }
    }
}
