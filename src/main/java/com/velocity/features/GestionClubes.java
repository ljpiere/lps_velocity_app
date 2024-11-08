package com.velocity.features;

import org.springframework.stereotype.Component;

@Component
public class GestionClubes implements Feature {
    @Override
    public String getFeatureDescription() {
        return "La característica de Gestion de Clubes está habilitada.";
    }
}
