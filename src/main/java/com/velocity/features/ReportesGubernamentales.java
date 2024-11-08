package com.velocity.features;

import org.springframework.stereotype.Component;

@Component
public class ReportesGubernamentales implements Feature {
    @Override
    public String getFeatureDescription() {
        return "La característica de Reportes Gubernamentales está habilitada.";
    }
}
