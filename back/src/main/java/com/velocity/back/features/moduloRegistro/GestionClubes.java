package com.velocity.back.features.moduloRegistro;

import com.velocity.back.club.Club;
import com.velocity.back.club.ClubRepository;
import com.velocity.back.user.AppUser;
import com.velocity.back.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@ConditionalOnProperty(name = "features.gestionClubes.enabled", havingValue = "true")
public class GestionClubes {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClubRepository clubRepository;

    public String inscribirEnClub(Long userId, String clubName) {
        Optional<AppUser> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return "Usuario no encontrado";
        }

        AppUser user = userOptional.get();

        // Buscar si el club ya existe, si no, crearlo
        Club club = new Club();
        club.setName(clubName);
        clubRepository.save(club);

        // Inscribir al usuario en el club

        return "Usuario " + user.getName() + " inscrito en el club " + clubName + " exitosamente";
    }
}
