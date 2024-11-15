package com.velocity.back.features.moduloRegistro;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "features.registroCiclistas.enabled", havingValue = "true")
public class RegistroCiclistas {
    public void registrarCiclista(String nombre, String bicicleta) {
        // Lógica para registrar un ciclista
        System.out.println("Registrando ciclista: " + nombre + " con bicicleta: " + bicicleta);
    }
}