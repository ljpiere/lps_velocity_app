package com.velocity.features;

import org.springframework.stereotype.Component;

@Component
public class CreacionEventos implements Feature {
    @Override
    public String getFeatureDescription() {
        return "La característica de Creacion de Eventos está habilitada.";
    }
}
