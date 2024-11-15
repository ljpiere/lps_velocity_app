package com.velocity.features;

import org.springframework.stereotype.Component;

@Component
public class ServiciosTerceros implements Feature {
    @Override
    public String getFeatureDescription() {
        return "La característica de Servicios de Terceros está habilitada.";
    }
}
