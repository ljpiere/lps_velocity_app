package com.velocity.controller;

import com.velocity.features.Feature;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FeatureController {

    private final List<Feature> enabledFeatures;

    public FeatureController(List<Feature> enabledFeatures) {
        this.enabledFeatures = enabledFeatures;
    }

    @GetMapping("/features")
    public String features(Model model) {
        List<String> featureDescriptions = enabledFeatures.stream()
                .map(Feature::getFeatureDescription)
                .collect(Collectors.toList());

        model.addAttribute("featureDescriptions", featureDescriptions);
        return "features";  // Esto corresponde a un archivo llamado "features.html" en la carpeta "templates"
    }
}
