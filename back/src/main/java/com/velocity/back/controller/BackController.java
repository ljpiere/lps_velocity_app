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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return "redirect:/auth/login";
        }
        model.addAttribute("message", "");
        model.addAttribute("serviciosTercerosEnabled", serviciosTercerosEnabled);
        model.addAttribute("gestionClubesEnabled", gestionClubesEnabled);
        model.addAttribute("gestionAlquilerEnabled", gestionAlquilerEnabled);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
            return "redirect:/";
        }
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("message", "Nombre de usuario o contraseña incorrectos.");
            return "auth/login";
        }
    }

    @GetMapping("/register")
    public String registerForm() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
            return "redirect:/";
        }
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String userName, @RequestParam String userUsername, @RequestParam String userPassword, Model model) {
        AppUser nuser = new AppUser();
        nuser.setName(userName);
        nuser.setUsername(userUsername);
        nuser.setPassword(new BCryptPasswordEncoder().encode(userPassword));  // Encriptar la contraseña
        userRepository.save(nuser);  // Guardar usuario en la base de datos

        model.addAttribute("message", "Usuario registrado exitosamente. Ahora puedes iniciar sesión.");
        return "auth/login";
    }

    @GetMapping("/registerBike")
    public String registerBikeForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return "redirect:/auth/login";
        }
        return "bike/registerBike";
    }

    @PostMapping("/registerBike")
    public String registerBike(@RequestParam String bikeName, @RequestParam String bikeBrand, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return "redirect:/auth/login";
        }

        String username = authentication.getName();
        AppUser user = userRepository.findByUsername(username);

        if (user == null) {
            model.addAttribute("message", "Usuario no encontrado.");
            return "bike/registerBike";
        }

        Bike bike = new Bike();
        bike.setId(System.currentTimeMillis()); // Asignar un ID único basado en el timestamp actual
        bike.setName(bikeName);
        bike.setBrand(bikeBrand);
        bike.setOwner(user);
        bikeRepository.save(bike);

        model.addAttribute("message", "Bicicleta registrada exitosamente.");
        return "redirect:/bike/myBikes";
    }


    @GetMapping("/myBikes")
    public String myBikes(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return "redirect:/auth/login";
        }

        String username = authentication.getName();
        AppUser user = userRepository.findByUsername(username);

        if (user == null) {
            model.addAttribute("message", "Usuario no encontrado.");
            return "index";
        }

        List<Bike> bikes = bikeRepository.findByOwner(user);
        model.addAttribute("bikes", bikes);
        return "bike/myBikes";
    }

    @GetMapping("/serviciosTerceros")
    public String serviciosTerceros(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return "redirect:/auth/login";
        }
        return "features/serviciosTerceros";
    }

    @GetMapping("/gestionClubes")
    public String gestionClubes(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return "redirect:/auth/login";
        }
        return "features/gestionClubes";
    }

    @PostMapping("/inscribirClub")
    public String inscribirClub(@RequestParam Long userId, @RequestParam String clubName, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return "redirect:/auth/login";
        }

        if (gestionClubes != null) {
            String resultado = gestionClubes.inscribirEnClub(userId, clubName);
            model.addAttribute("message", resultado);
        } else {
            model.addAttribute("message", "La funcionalidad de gestión de clubes no está habilitada.");
        }
        return "features/gestionClubes";
    }

    @GetMapping("/gestionAlquiler")
    public String gestionAlquiler(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return "redirect:/auth/login";
        }

        String username = authentication.getName();
        AppUser user = userRepository.findByUsername(username);

        if (user == null) {
            model.addAttribute("message", "Usuario no encontrado.");
            return "alquiler/gestionAlquiler";
        }

        List<Bike> bikes = bikeRepository.findByOwner(user);
        model.addAttribute("bikes", bikes);
        return "alquiler/gestionAlquiler";
    }

    @PostMapping("/alquilarBicicleta")
    public String alquilarBicicleta(@RequestParam Long bikeId, Model model) {
        // Obtener el usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return "redirect:/auth/login";
        }

        String username = authentication.getName();
        AppUser user = userRepository.findByUsername(username);

        if (user == null) {
            model.addAttribute("message", "Usuario no encontrado.");
            return "alquiler/gestionAlquiler";
        }

        // Validar si la bicicleta existe
        Optional<Bike> bikeOptional = bikeRepository.findById(bikeId);
        if (bikeOptional.isEmpty()) {
            model.addAttribute("message", "Bicicleta no encontrada. Por favor, selecciona una bicicleta válida.");
            return "alquiler/gestionAlquiler";
        }

        // Verificar si la funcionalidad de alquiler está habilitada
        if (gestionAlquiler != null) {
            String resultado = gestionAlquiler.alquilarBicicleta(user.getId(), bikeId);
            model.addAttribute("message", resultado);
        } else {
            model.addAttribute("message", "La funcionalidad de gestión de alquiler no está habilitada.");
        }
        
        return "alquiler/gestionAlquiler";
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
