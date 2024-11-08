package com.velocity.features;

import org.springframework.stereotype.Component;

@Component
public class ForosDiscusion implements Feature {
    @Override
    public String getFeatureDescription() {
        return "La característica de Foros de Discusion está habilitada.";
    }
}
