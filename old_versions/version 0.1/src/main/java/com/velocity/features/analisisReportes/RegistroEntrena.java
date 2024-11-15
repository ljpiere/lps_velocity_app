package com.velocity.features.analisisReportes;

import com.velocity.config.ConfigManager;

public class RegistroEntrena {
    private ConfigManager configManager;

    public RegistroEntrena(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void register() {
        if (configManager.isFeatureEnabled("analisisReportes.RegistroEntrena")) {
            System.out.println("RegistroEntrena está habilitado.");
            // Implementación de la lógica
        } else {
            System.out.println("RegistroEntrena está deshabilitado.");
        }
    }
}
