package com.velocity.features;

import org.springframework.stereotype.Component;

@Component
public class AnalisisRendimiento implements Feature {
    @Override
    public String getFeatureDescription() {
        return "La característica de Analisis de Rendimiento está habilitada.";
    }
}
