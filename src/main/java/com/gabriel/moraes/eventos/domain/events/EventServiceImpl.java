package com.gabriel.moraes.eventos.domain.events;

import com.gabriel.moraes.eventos.domain.attendee.Attendee;
import com.gabriel.moraes.eventos.domain.attendee.AttendeeServiceImpl;
import com.gabriel.moraes.eventos.domain.attendee.payloads.AttendeeIdDTO;
import com.gabriel.moraes.eventos.domain.attendee.payloads.AttendeeRequestDTO;
import com.gabriel.moraes.eventos.domain.events.exceptions.EventIsFullException;
import com.gabriel.moraes.eventos.domain.events.exceptions.EventNotFoundException;
import com.gabriel.moraes.eventos.domain.events.payloads.EventIdDTO;
import com.gabriel.moraes.eventos.domain.events.payloads.EventRequestDTO;
import com.gabriel.moraes.eventos.domain.events.payloads.EventResponseDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final AttendeeServiceImpl attendeeService;
    private final ModelMapper mapper;

    @Override
    public EventIdDTO createEvent(EventRequestDTO eventRequestDTO) {
        Event event = mapper.map(eventRequestDTO, Event.class);
        event.setSlug(createSlug(event.getTitle()));

        eventRepository.save(event);

        return new EventIdDTO(event.getId());
    }

    @Override
    public EventResponseDTO getEventDetail(String id) {
        Event event = getEventById(id);
        return mapper.map(event, EventResponseDTO.class);
    }

    @Override
    public AttendeeIdDTO registerAttendeeOnEvent(String eventId, AttendeeRequestDTO attendeeRequestDTO){
        attendeeService.verifyAttendeeSubscription(attendeeRequestDTO.getEmail(), eventId);

        Event event = getEventById(eventId);

        List<Attendee> attendeeList = attendeeService.getAllAttendeesFromEvent(eventId);

        if (event.getMaximumAttendees() <= attendeeList.size()) throw new EventIsFullException("Event is Full");

        Attendee attendee = mapper.map(attendeeRequestDTO, Attendee.class);
        attendee.setEvent(event);
        attendee.setCreatedAt(LocalDateTime.now());

        attendeeService.registerAttendee(attendee);

        return new AttendeeIdDTO(attendee.getId());
    }

    private String createSlug(String text){
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCOMBINING_DIACRITICAL_MARKS}", "")
                .replaceAll("[^\\w\\s]","")
                .replaceAll("\\s+", "-")
                .toLowerCase();
    }

    private Event getEventById(String eventId){
        return this.eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not find with ID: " + eventId));
    }
}
