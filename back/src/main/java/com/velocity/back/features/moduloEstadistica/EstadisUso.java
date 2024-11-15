package com.velocity.back.features.moduloEstadistica;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "features.estadisUso.enabled", havingValue = "true")
public class EstadisUso {
    public void mostrarEstadisticas(String usuario) {
        // Lógica para mostrar los kms recorridos por el usuario
        System.out.println("Mostrando estadísticas para el usuario: " + usuario);
    }
}