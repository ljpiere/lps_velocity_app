package com.velocity.back.features.moduloEventos;

import com.velocity.back.event.Event;
import com.velocity.back.event.EventRepository;
import com.velocity.back.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreacionEventos {

    @Autowired
    private EventRepository eventRepository;

    // Método para crear un evento y guardarlo en la base de datos
    public String crearEvento(String eventName, String eventDate, AppUser owner) {
        Event event = new Event();
        event.setName(eventName);
        event.setDate(eventDate);
        event.setOwner(owner);
        eventRepository.save(event);
        return "Evento creado exitosamente.";
    }

    // Método para obtener todos los eventos creados por un usuario
    public List<Event> obtenerEventos(AppUser owner) {
        return eventRepository.findByOwner(owner);
    }
}
