package com.velocity.features.analisisReportes;

import com.velocity.config.ConfigManager;

public class ReportesGobierno {
    private ConfigManager configManager;

    public ReportesGobierno(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void register() {
        if (configManager.isFeatureEnabled("analisisReportes.ReportesGobierno")) {
            System.out.println("ReportesGobierno está habilitado.");
            // Implementación de la lógica
        } else {
            System.out.println("ReportesGobierno está deshabilitado.");
        }
    }
}
