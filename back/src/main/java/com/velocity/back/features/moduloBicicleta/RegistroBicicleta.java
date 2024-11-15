package com.velocity.back.features.moduloBicicleta;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "features.registroBicicleta.enabled", havingValue = "true")
public class RegistroBicicleta {
    public void registrarBicicleta(String nombre) {
        // Lógica para registrar una bicicleta
        System.out.println("Registrando bicicleta: " + nombre);
    }
}