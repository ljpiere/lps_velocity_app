package com.velocity.features.analisisReportes;

import com.velocity.config.ConfigManager;

public class AnalisisRendimiento {
    private ConfigManager configManager;

    public AnalisisRendimiento(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void register() {
        if (configManager.isFeatureEnabled("analisisReportes.AnalisiRendmiento")) {
            System.out.println("AnalisiRendmiento está habilitado.");
            // Implementación de la lógica
        } else {
            System.out.println("AnalisiRendmiento está deshabilitado.");
        }
    }
}
