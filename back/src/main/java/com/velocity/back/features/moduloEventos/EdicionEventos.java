package com.velocity.back.features.moduloEventos;

import com.velocity.back.event.Event;
import com.velocity.back.event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EdicionEventos {

    @Autowired
    private EventRepository eventRepository;

    public String editarEvento(Long eventId, String newEventName, String newEventDate) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (eventOptional.isEmpty()) {
            return "Evento no encontrado.";
        }

        Event event = eventOptional.get();
        event.setName(newEventName);
        event.setDate(newEventDate);
        eventRepository.save(event);
        return "Evento actualizado exitosamente.";
    }
}
