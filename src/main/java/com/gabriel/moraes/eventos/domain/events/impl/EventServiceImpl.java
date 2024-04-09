package com.gabriel.moraes.eventos.domain.events.impl;

import com.gabriel.moraes.eventos.domain.events.Event;
import com.gabriel.moraes.eventos.domain.events.EventRepository;
import com.gabriel.moraes.eventos.domain.events.EventService;
import com.gabriel.moraes.eventos.domain.events.exceptions.EventNotFoundException;
import com.gabriel.moraes.eventos.domain.events.payloads.EventRequest;
import com.gabriel.moraes.eventos.domain.events.payloads.EventResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.Normalizer;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ModelMapper mapper;

    @Override
    public EventResponse createEvent(EventRequest eventRequest) {
        Event event = mapper.map(eventRequest, Event.class);
        System.out.println(event.getTitle());
        event.setSlug(createSlug(event.getTitle()));

        return  mapper.map(eventRepository.save(event), EventResponse.class);
    }

    @Override
    public EventResponse getEventById(String id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Product not found with id: " + id));

        return mapper.map(event, EventResponse.class);
    }

    private String createSlug(String text){
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCOMBINING_DIACRITICAL_MARKS}", "")
                .replaceAll("[^\\w\\s]","")
                .replaceAll("\\s+", "-")
                .toLowerCase();
    }
}
