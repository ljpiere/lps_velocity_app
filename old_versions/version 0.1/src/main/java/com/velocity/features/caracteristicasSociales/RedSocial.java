package com.velocity.features.caracteristicasSociales;

import com.velocity.config.ConfigManager;

public class RedSocial {
    private ConfigManager configManager;

    public RedSocial(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void register() {
        if (configManager.isFeatureEnabled("caracteristicasSociales.RedSocial")) {
            System.out.println("RedSocial está habilitado.");
            // Implementación de la lógica
        } else {
            System.out.println("RedSocial está deshabilitado.");
        }
    }
}
