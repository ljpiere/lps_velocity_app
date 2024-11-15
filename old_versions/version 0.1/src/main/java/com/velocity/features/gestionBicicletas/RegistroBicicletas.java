package com.velocity.features.gestionBicicletas;

import com.velocity.config.ConfigManager;

public class RegistroBicicletas {
    private ConfigManager configManager;

    public RegistroBicicletas(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void register() {
        if (configManager.isFeatureEnabled("gestionBicicletas.RegistroBicicletas")) {
            System.out.println("RegistroBicicletas está habilitado.");
            // Implementación de la lógica
        } else {
            System.out.println("RegistroBicicletas está deshabilitado.");
        }
    }
}
