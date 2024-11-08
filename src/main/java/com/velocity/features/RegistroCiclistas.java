package com.velocity.features;

import org.springframework.stereotype.Component;

@Component
public class RegistroCiclistas implements Feature {
    @Override
    public String getFeatureDescription() {
        return "La característica de Registro de Ciclistas está habilitada.";
    }
}
