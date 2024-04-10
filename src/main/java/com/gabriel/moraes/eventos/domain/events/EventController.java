package com.gabriel.moraes.eventos.domain.events;

import com.gabriel.moraes.eventos.domain.attendee.AttendeeService;
import com.gabriel.moraes.eventos.domain.attendee.payloads.AttendeeIdDTO;
import com.gabriel.moraes.eventos.domain.attendee.payloads.AttendeeRequestDTO;
import com.gabriel.moraes.eventos.domain.attendee.payloads.AttendeesListResponseDTO;
import com.gabriel.moraes.eventos.domain.events.payloads.EventIdDTO;
import com.gabriel.moraes.eventos.domain.events.payloads.EventRequestDTO;
import com.gabriel.moraes.eventos.domain.events.payloads.EventResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final AttendeeService attendeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id){
        return new ResponseEntity<>(eventService.getEventDetail(id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@Valid @RequestBody EventRequestDTO eventRequestDTO, UriComponentsBuilder uriComponentsBuilder){
        EventIdDTO eventIdDTO = eventService.createEvent(eventRequestDTO);

        var uri = uriComponentsBuilder.path("/v1/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();

        return ResponseEntity.created(uri).body(eventIdDTO);
    }

    @PostMapping("/{eventId}/attendees")
    public ResponseEntity<AttendeeIdDTO> registerParticipant(@PathVariable String eventId, @Valid @RequestBody AttendeeRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
        AttendeeIdDTO attendeeIdDTO = eventService.registerAttendeeOnEvent(eventId, body);

        var uri = uriComponentsBuilder.path("/v1/attendees/{attendeeId}/badge").buildAndExpand(attendeeIdDTO.attendeeId()).toUri();

        return ResponseEntity.created(uri).body(attendeeIdDTO);
    }


    @GetMapping("/{eventId}/attendees/")
    public ResponseEntity<AttendeesListResponseDTO> getEventAttendees(@PathVariable String eventId){
        AttendeesListResponseDTO attendeesListResponseDTO = attendeeService.getEventsAttendee(eventId);

        return ResponseEntity.ok(attendeesListResponseDTO);
    }


}
