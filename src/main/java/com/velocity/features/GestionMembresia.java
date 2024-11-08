package com.velocity.features;

import org.springframework.stereotype.Component;

@Component
public class GestionMembresia implements Feature {
    @Override
    public String getFeatureDescription() {
        return "La característica de Gestion de Membresia está habilitada.";
    }
}
