package com.velocity.features;

import org.springframework.stereotype.Component;

@Component
public class CompartirRutas implements Feature {
    @Override
    public String getFeatureDescription() {
        return "La característica de Compartir Rutas está habilitada.";
    }
}
