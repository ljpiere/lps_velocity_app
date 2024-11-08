package com.velocity.config;

import com.velocity.features.Feature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Configuration
public class FeatureConfig {

    private static final Logger logger = Logger.getLogger(FeatureConfig.class.getName());

    @Value("${app.features-config}")
    private String featuresConfigPath;

    private final ApplicationContext context;

    public FeatureConfig(ApplicationContext context) {
        this.context = context;
    }

    @Bean
    public List<Feature> enabledFeatures() {
        List<Feature> enabledFeatures = new ArrayList<>();
        try {
            if (featuresConfigPath == null || featuresConfigPath.isEmpty()) {
                throw new IllegalArgumentException("La propiedad 'features-config' no está configurada.");
            }

            File xmlFile = new File(featuresConfigPath);
            if (!xmlFile.exists()) {
                throw new IllegalArgumentException("El archivo de configuración de características no existe: " + featuresConfigPath);
            }

            logger.info("Cargando características desde: " + featuresConfigPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList featureList = doc.getElementsByTagName("feature");
            for (int i = 0; i < featureList.getLength(); i++) {
                Element featureElement = (Element) featureList.item(i);
                String featureName = featureElement.getAttribute("name");
                if (featureName == null || featureName.isEmpty()) {
                    logger.warning("Característica sin nombre encontrada en el XML. Ignorando.");
                    continue;
                }

                try {
                    String className = "com.velocity.features." + featureName;
                    Feature featureBean = (Feature) context.getBean(Class.forName(className));
                    enabledFeatures.add(featureBean);
                    logger.info("Característica cargada exitosamente: " + featureName);
                } catch (ClassNotFoundException e) {
                    logger.warning("No se encontró la clase para la característica: " + featureName);
                } catch (Exception e) {
                    logger.severe("Error al cargar la característica: " + featureName + ". Error: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            logger.severe("Error al cargar características: " + e.getMessage());
            e.printStackTrace();
        }

        if (enabledFeatures.isEmpty()) {
            logger.warning("No se habilitaron características. Verifica el archivo de configuración y las clases de características.");
        }

        return enabledFeatures;
    }
}
