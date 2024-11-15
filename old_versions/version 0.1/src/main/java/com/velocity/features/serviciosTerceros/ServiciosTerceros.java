package com.velocity.features.serviciosTerceros;

import com.velocity.config.ConfigManager;

public class ServiciosTerceros {
    private ConfigManager configManager;

    public ServiciosTerceros(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void register() {
        if (configManager.isFeatureEnabled("serviciosTerceros.ServiciosTerceros")) {
            System.out.println("ServiciosTerceros está habilitado.");
            // Implementación de la lógica
        } else {
            System.out.println("ServiciosTerceros está deshabilitado.");
        }
    }
}
