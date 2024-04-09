package com.gabriel.moraes.eventos.domain.events;

import com.gabriel.moraes.eventos.domain.events.payloads.EventRequest;
import com.gabriel.moraes.eventos.domain.events.payloads.EventResponse;


public interface EventService {
    EventResponse createEvent(EventRequest eventRequest);

    EventResponse getEventById(String id);

}
