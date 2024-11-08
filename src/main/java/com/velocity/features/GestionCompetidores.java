package com.velocity.features;

import org.springframework.stereotype.Component;

@Component
public class GestionCompetidores implements Feature {
    @Override
    public String getFeatureDescription() {
        return "La característica de Gestion de Competidores está habilitada.";
    }
}
