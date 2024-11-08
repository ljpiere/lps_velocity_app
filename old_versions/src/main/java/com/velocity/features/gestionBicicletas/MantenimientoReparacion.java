package com.velocity.features.gestionBicicletas;

import com.velocity.config.ConfigManager;

public class MantenimientoReparacion {
    private ConfigManager configManager;

    public MantenimientoReparacion(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void register() {
        if (configManager.isFeatureEnabled("gestionBicicletas.MantenimientoReparacion")) {
            System.out.println("MantenimientoReparacion está habilitado.");
            // Implementación de la lógica
        } else {
            System.out.println("MantenimientoReparacion está deshabilitado.");
        }
    }
}
