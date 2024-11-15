package com.velocity.back.features.serviciosComplementarios;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "features.serviciosTerceros.enabled", havingValue = "true")
public class ServiciosTerceros {
    public void mostrarServicios() {
        // Lógica para mostrar servicios de terceros
        System.out.println("Mostrando servicios de terceros");
    }
}