package com.velocity.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private Properties properties;

    public ConfigManager() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("features.properties")) {
            if (input == null) {
                System.out.println("Lo siento, no se encontró el archivo de configuración.");
                return;
            }
            // Cargar las propiedades del archivo
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Método para verificar si una característica está habilitada
    public boolean isFeatureEnabled(String featureName) {
        return Boolean.parseBoolean(properties.getProperty(featureName, "false"));
    }
}
