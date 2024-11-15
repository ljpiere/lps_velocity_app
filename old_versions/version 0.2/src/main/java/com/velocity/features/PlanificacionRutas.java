package com.velocity.features;

import org.springframework.stereotype.Component;

@Component
public class PlanificacionRutas implements Feature {
    @Override
    public String getFeatureDescription() {
        return "La característica de Planificacion de Rutas está habilitada.";
    }
}
