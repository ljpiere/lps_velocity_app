package com.velocity.features;

import org.springframework.stereotype.Component;

@Component
public class MantenimientoBicicletas implements Feature {
    @Override
    public String getFeatureDescription() {
        return "La característica de Mantenimiento de Bicicletas está habilitada.";
    }
}
