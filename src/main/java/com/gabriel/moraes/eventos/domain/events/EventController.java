package com.gabriel.moraes.eventos.domain.events;

import com.gabriel.moraes.eventos.domain.attendee.AttendeeService;
import com.gabriel.moraes.eventos.domain.attendee.payloads.AttendeeIdDTO;
import com.gabriel.moraes.eventos.domain.attendee.payloads.AttendeeRequestDTO;
import com.gabriel.moraes.eventos.domain.attendee.payloads.AttendeesListResponseDTO;
import com.gabriel.moraes.eventos.domain.events.payloads.EventIdDTO;
import com.gabriel.moraes.eventos.domain.events.payloads.EventRequestDTO;
import com.gabriel.moraes.eventos.domain.events.payloads.EventResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/v1/events")
@RequiredArgsConstructor
@Tag(name = "Events", description = "Endpoints related to event operations")
public class EventController {

    private final EventService eventService;
    private final AttendeeService attendeeService;

    @GetMapping("/{id}")
    @Operation(summary = "Get event details", responses = {
            @ApiResponse(responseCode = "200", description = "Event details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id) {
        EventResponseDTO eventResponseDTO = eventService.getEventDetail(id);
        return ResponseEntity.ok(eventResponseDTO);
    }

    @PostMapping
    @Operation(summary = "Create a new event", responses = {
            @ApiResponse(responseCode = "201", description = "Event created successfully")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<EventIdDTO> createEvent(@Valid @RequestBody EventRequestDTO eventRequestDTO, UriComponentsBuilder uriComponentsBuilder) {
        EventIdDTO eventIdDTO = eventService.createEvent(eventRequestDTO);
        var uri = uriComponentsBuilder.path("/v1/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();
        return ResponseEntity.created(uri).body(eventIdDTO);
    }

    @PostMapping("/{eventId}/attendees")
    @Operation(summary = "Register an attendee to an event", responses = {
            @ApiResponse(responseCode = "201", description = "Attendee registered successfully"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    public ResponseEntity<AttendeeIdDTO> registerAttendee(@PathVariable String eventId, @Valid @RequestBody AttendeeRequestDTO attendeeRequestDTO, UriComponentsBuilder uriComponentsBuilder) {
        AttendeeIdDTO attendeeIdDTO = eventService.registerAttendeeOnEvent(eventId, attendeeRequestDTO);
        var uri = uriComponentsBuilder.path("/v1/attendees/{attendeeId}/badge").buildAndExpand(attendeeIdDTO.attendeeId()).toUri();
        return ResponseEntity.created(uri).body(attendeeIdDTO);
    }

    @GetMapping("/{eventId}/attendees")
    @Operation(summary = "Get list of attendees for an event", responses = {
            @ApiResponse(responseCode = "200", description = "List of attendees retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<AttendeesListResponseDTO> getEventAttendees(@PathVariable String eventId) {
        AttendeesListResponseDTO attendeesListResponseDTO = attendeeService.getEventsAttendee(eventId);
        return ResponseEntity.ok(attendeesListResponseDTO);
    }
}