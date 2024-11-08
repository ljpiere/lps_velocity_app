package com.velocity.features;

import org.springframework.stereotype.Component;

@Component
public class RegistroBicicleta implements Feature {
    @Override
    public String getFeatureDescription() {
        return "La característica de Registro de Bicicleta está habilitada.";
    }
}
