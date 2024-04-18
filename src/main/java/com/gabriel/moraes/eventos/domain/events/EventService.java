package com.gabriel.moraes.eventos.domain.events;

import com.gabriel.moraes.eventos.domain.attendee.payloads.AttendeeIdDTO;
import com.gabriel.moraes.eventos.domain.attendee.payloads.AttendeeRequestDTO;
import com.gabriel.moraes.eventos.domain.events.payloads.EventIdDTO;
import com.gabriel.moraes.eventos.domain.events.payloads.EventRequestDTO;
import com.gabriel.moraes.eventos.domain.events.payloads.EventResponseDTO;


public interface EventService {
    EventIdDTO createEvent(EventRequestDTO eventRequestDTO);

    AttendeeIdDTO registerAttendeeOnEvent(String eventId, AttendeeRequestDTO attendeeRequestDTO);

    EventResponseDTO getEventDetail(String id);
}
