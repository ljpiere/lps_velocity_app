package com.velocity.features;

import org.springframework.stereotype.Component;

@Component
public class EstadisticasUso implements Feature {
    @Override
    public String getFeatureDescription() {
        return "La característica de Estadisticas de Uso está habilitada.";
    }
}
