package com.velocity.back.controller;

import com.velocity.back.bike.Bike;
import com.velocity.back.bike.BikeRepository;
import com.velocity.back.club.ClubRepository;
import com.velocity.back.alquiler.AlquilerRepository;
import com.velocity.back.user.AppUser;
import com.velocity.back.user.UserRepository;
import com.velocity.back.features.moduloRegistro.RegistroCiclistas;
import com.velocity.back.features.moduloBicicleta.RegistroBicicleta;
import com.velocity.back.features.moduloRegistro.GestionClubes;
import com.velocity.back.features.moduloBicicleta.GestionAlquiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BackController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private AlquilerRepository alquilerRepository;

    @Autowired(required = false)
    private RegistroCiclistas registroCiclistas;

    @Autowired(required = false)
    private RegistroBicicleta registroBicicleta;

    @Autowired(required = false)
    private GestionClubes gestionClubes;

    @Autowired(required = false)
    private GestionAlquiler gestionAlquiler;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "");
        model.addAttribute("serviciosTercerosEnabled", serviciosTercerosEnabled);
        model.addAttribute("gestionClubesEnabled", gestionClubesEnabled);
        model.addAttribute("gestionAlquilerEnabled", gestionAlquilerEnabled);
        return "index";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String userName, @RequestParam String bikeName, Model model) {
        AppUser nuser = new AppUser();
        nuser.setName(userName);
        userRepository.save(nuser);  // Guardar usuario en la base de datos

        Bike bike = new Bike();
        bike.setName(bikeName);
        bikeRepository.save(bike);  // Guardar bicicleta en la base de datos

        if (registroCiclistas != null) {
            registroCiclistas.registrarCiclista(nuser.getName(), bike.getName());
        }

        if (registroBicicleta != null) {
            registroBicicleta.registrarBicicleta(bike.getName());
        }

        model.addAttribute("message", "Usuario y bicicleta registrados exitosamente");
        model.addAttribute("serviciosTercerosEnabled", serviciosTercerosEnabled);
        model.addAttribute("gestionClubesEnabled", gestionClubesEnabled);
        model.addAttribute("gestionAlquilerEnabled", gestionAlquilerEnabled);
        return "index";
    }

    @GetMapping("/serviciosTerceros")
    public String serviciosTerceros() {
        return "serviciosTerceros";
    }

    @GetMapping("/gestionClubes")
    public String gestionClubes() {
        return "gestionClubes";
    }

    @PostMapping("/inscribirClub")
    public String inscribirClub(@RequestParam Long userId, @RequestParam String clubName, Model model) {
        if (gestionClubes != null) {
            String resultado = gestionClubes.inscribirEnClub(userId, clubName);
            model.addAttribute("message", resultado);
        } else {
            model.addAttribute("message", "La funcionalidad de gestión de clubes no está habilitada.");
        }
        return "gestionClubes";
    }

    @GetMapping("/gestionAlquiler")
    public String gestionAlquiler() {
        return "gestionAlquiler";
    }

    @PostMapping("/alquilarBicicleta")
    public String alquilarBicicleta(@RequestParam Long userId, @RequestParam Long bikeId, Model model) {
        if (gestionAlquiler != null) {
            String resultado = gestionAlquiler.alquilarBicicleta(userId, bikeId);
            model.addAttribute("message", resultado);
        } else {
            model.addAttribute("message", "La funcionalidad de gestión de alquiler no está habilitada.");
        }
        return "gestionAlquiler";
    }

    @Autowired
    @org.springframework.beans.factory.annotation.Value("${features.serviciosTerceros.enabled:false}")
    private boolean serviciosTercerosEnabled;

    @Autowired
    @org.springframework.beans.factory.annotation.Value("${features.gestionClubes.enabled:false}")
    private boolean gestionClubesEnabled;

    @Autowired
    @org.springframework.beans.factory.annotation.Value("${features.gestionAlquiler.enabled:false}")
    private boolean gestionAlquilerEnabled;
}