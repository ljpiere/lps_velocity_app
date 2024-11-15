package com.velocity.back.features.moduloBicicleta;

import com.velocity.back.alquiler.Alquiler;
import com.velocity.back.alquiler.AlquilerRepository;
import com.velocity.back.user.UserRepository;
import com.velocity.back.bike.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@ConditionalOnProperty(name = "features.gestionAlquiler.enabled", havingValue = "true")
public class GestionAlquiler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private AlquilerRepository alquilerRepository;

    public String alquilarBicicleta(Long userId, Long bikeId) {
        if (userRepository.findById(userId).isEmpty()) {
            return "Usuario no encontrado";
        }
        if (bikeRepository.findById(bikeId).isEmpty()) {
            return "Bicicleta no encontrada";
        }

        Alquiler alquiler = new Alquiler();
        alquiler.setUserId(userId);
        alquiler.setBikeId(bikeId);
        alquilerRepository.save(alquiler);

        return "Usuario con ID " + userId + " ha alquilado la bicicleta con ID " + bikeId + " exitosamente";
    }
}