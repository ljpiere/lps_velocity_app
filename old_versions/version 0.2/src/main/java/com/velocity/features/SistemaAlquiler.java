package com.velocity.features;

import org.springframework.stereotype.Component;

@Component
public class SistemaAlquiler implements Feature {
    @Override
    public String getFeatureDescription() {
        return "La característica de Sistema de Alquiler está habilitada.";
    }
}
