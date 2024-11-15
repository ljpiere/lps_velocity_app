package com.velocity.features.analisisReportes;

import com.velocity.config.ConfigManager;

public class EstadisticasUso {
    private ConfigManager configManager;

    public EstadisticasUso(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void register() {
        if (configManager.isFeatureEnabled("analisisReportes.EstadisticasUso")) {
            System.out.println("EstadisticasUso está habilitado.");
            // Implementación de la lógica
        } else {
            System.out.println("EstadisticasUso está deshabilitado.");
        }
    }
}
