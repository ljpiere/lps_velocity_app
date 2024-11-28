package com.velocity.back.controller;

import com.velocity.back.bike.Bike;
import com.velocity.back.bike.BikeRepository;
import com.velocity.back.club.ClubRepository;
import com.velocity.back.alquiler.AlquilerRepository;
import com.velocity.back.user.AppUser;
import com.velocity.back.user.UserRepository;
import com.velocity.back.event.Event;
import com.velocity.back.features.moduloRegistro.RegistroCiclistas;
import com.velocity.back.features.moduloBicicleta.RegistroBicicleta;
import com.velocity.back.features.moduloEventos.CreacionEventos;
import com.velocity.back.features.moduloEventos.EdicionEventos;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

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

    @Autowired(required = false)
    private CreacionEventos creacionEventos;

    @Autowired(required = false)
    private EdicionEventos edicionEventos;

    @Autowired
    @org.springframework.beans.factory.annotation.Value("${features.reportesGobierno.enabled:false}")
    private boolean reportesGobiernoEnabled;

    @Autowired
    private AuthenticationManager authenticationManager;

    private List<Map<String, String>> posts = new ArrayList<>(); // Lista de publicaciones

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
        model.addAttribute("registroCiclistasEnabled", registroCiclistasEnabled);
        model.addAttribute("redSocialEnabled", redSocialEnabled);
        model.addAttribute("estadisUsoEnabled", estadisUsoEnabled);
        model.addAttribute("registroBicicletaEnabled", registroBicicletaEnabled);
        model.addAttribute("creacionEventosEnabled", creacionEventosEnabled);
        model.addAttribute("edicionEventosEnabled", edicionEventosEnabled);
        model.addAttribute("reportesGobiernoEnabled", reportesGobiernoEnabled);
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
        return "redirect:/";
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

    @PostMapping("/crearEvento")
    public String crearEvento(@RequestParam String eventName, @RequestParam String eventDate, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return "redirect:/auth/login";
        }

        String username = authentication.getName();
        AppUser user = userRepository.findByUsername(username);

        if (user == null) {
            model.addAttribute("message", "Usuario no encontrado.");
            return "eventos/crearEvento";
        }

        if (creacionEventos != null) {
            String resultado = creacionEventos.crearEvento(eventName, eventDate, user);
            model.addAttribute("message", resultado);
        } else {
            model.addAttribute("message", "La funcionalidad de creación de eventos no está habilitada.");
        }

        return "redirect:/misEventos";
    }


    @GetMapping("/crearEvento")
    public String crearEventoForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return "redirect:/auth/login";
        }
        return "eventos/crearEvento"; // Mostrar la vista de creación de eventos
    }

    @GetMapping("/editarEvento")
    public String editarEventoForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return "redirect:/auth/login";
        }

        String username = authentication.getName();
        AppUser user = userRepository.findByUsername(username);

        if (user == null) {
            model.addAttribute("message", "Usuario no encontrado.");
            return "eventos/editarEvento";
        }

        List<Event> events = creacionEventos.obtenerEventos(user);
        model.addAttribute("events", events);
        return "eventos/editarEvento";
    }

    @GetMapping("/misEventos")
    public String misEventos(Model model) {
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

        List<Event> events = creacionEventos.obtenerEventos(user);
        model.addAttribute("events", events);
        return "eventos/misEventos";
    }

    @GetMapping("/redSocial")
    public String redSocial(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return "redirect:/auth/login";
        }

        // Aquí simplemente añadimos publicaciones ficticias
        List<Map<String, String>> posts = new ArrayList<>();
        Map<String, String> post1 = new HashMap<>();
        post1.put("usuario", "Usuario1");
        post1.put("contenido", "Este es un mensaje ficticio de la red social.");
        posts.add(post1);

        model.addAttribute("posts", posts);
        return "social/red_social";
    }

    @PostMapping("/crearPublicacion")
    public String crearPublicacion(@RequestParam String contenido, Model model) {
        // En un caso real, aquí almacenarías el contenido en la base de datos o una lista compartida.
        model.addAttribute("message", "Publicación creada exitosamente.");

        // Redirige nuevamente a la página de la red social
        return "redirect:/redSocial";
    }

    @GetMapping("/estadisticasUso")
    public String estadisticasUso(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return "redirect:/auth/login";
        }

        // Datos ficticios de estadísticas de uso
        List<Map<String, String>> estadisticas = new ArrayList<>();
        
        Map<String, String> stat1 = new HashMap<>();
        stat1.put("nombre", "Kilómetros recorridos");
        stat1.put("valor", "150 km");
        estadisticas.add(stat1);

        Map<String, String> stat2 = new HashMap<>();
        stat2.put("nombre", "Horas de uso");
        stat2.put("valor", "12 horas");
        estadisticas.add(stat2);

        Map<String, String> stat3 = new HashMap<>();
        stat3.put("nombre", "Número de viajes");
        stat3.put("valor", "8 viajes");
        estadisticas.add(stat3);

        // Añadir las estadísticas al modelo
        model.addAttribute("estadisticas", estadisticas);

        return "estadisticas/estadisticas_uso";
    }
    
    @GetMapping("/reportesGobierno")
    public String reportesGobierno(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return "redirect:/auth/login";
        }

        // Datos ficticios de reportes para el gobierno
        List<Map<String, Object>> reportes = new ArrayList<>();

        Map<String, Object> reporte1 = new HashMap<>();
        reporte1.put("usuario", "Juan Perez");
        reporte1.put("usoBicicleta", "200 km recorridos, 15 horas de uso");
        List<String> eventosParticipados1 = new ArrayList<>();
        eventosParticipados1.add("Evento: Corre mi tierra");
        eventosParticipados1.add("Evento: Gran Fondo 2024");
        reporte1.put("eventosParticipados", eventosParticipados1);
        reportes.add(reporte1);

        Map<String, Object> reporte2 = new HashMap<>();
        reporte2.put("usuario", "Maria Lopez");
        reporte2.put("usoBicicleta", "120 km recorridos, 8 horas de uso");
        List<String> eventosParticipados2 = new ArrayList<>();
        eventosParticipados2.add("Evento: Vuelta al Lago");
        reporte2.put("eventosParticipados", eventosParticipados2);
        reportes.add(reporte2);

        // Añadir los reportes al modelo
        model.addAttribute("reportes", reportes);

        return "reportes/reportes_gobierno";
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

    @Autowired
    @org.springframework.beans.factory.annotation.Value("${features.registroCiclistas.enabled:false}")
    private boolean registroCiclistasEnabled;

    @Autowired
    @org.springframework.beans.factory.annotation.Value("${features.redSocial.enabled:false}")
    private boolean redSocialEnabled;

    @Autowired
    @org.springframework.beans.factory.annotation.Value("${features.estadisUso.enabled:false}")
    private boolean estadisUsoEnabled;

    @Autowired
    @org.springframework.beans.factory.annotation.Value("${features.registroBicicleta.enabled:false}")
    private boolean registroBicicletaEnabled;

    @Autowired
    @org.springframework.beans.factory.annotation.Value("${features.creacionEventos.enabled:false}")
    private boolean creacionEventosEnabled;

    @Autowired
    @org.springframework.beans.factory.annotation.Value("${features.edicionEventos.enabled:false}")
    private boolean edicionEventosEnabled;
}
