package com.velocity.features;

import org.springframework.stereotype.Component;

@Component
public class RedSocial implements Feature {
    @Override
    public String getFeatureDescription() {
        return "La característica de Red Social está habilitada.";
    }
}
