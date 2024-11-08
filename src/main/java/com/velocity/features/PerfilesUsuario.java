package com.velocity.features;

import org.springframework.stereotype.Component;

@Component
public class PerfilesUsuario implements Feature {
    @Override
    public String getFeatureDescription() {
        return "La característica de Perfiles de Usuario está habilitada.";
    }
}
