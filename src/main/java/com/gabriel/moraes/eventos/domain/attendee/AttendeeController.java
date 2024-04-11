package com.gabriel.moraes.eventos.domain.attendee;

import com.gabriel.moraes.eventos.domain.attendee.payloads.AttendeeBadgeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/attendees")
@Tag(name = "Attendees", description = "Endpoints related to attendee operations")
public class AttendeeController {

    private final AttendeeService attendeeService;

    @GetMapping("/{attendeeId}/badge")
    @Operation(summary = "Get attendee badge", description = "Retrieve the badge details for a specific attendee")
    public ResponseEntity<AttendeeBadgeResponseDTO> getAttendeeBadge(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
        AttendeeBadgeResponseDTO response = attendeeService.getAttendeeBadge(attendeeId, uriComponentsBuilder);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{attendeeId}/check-in")
    @Operation(summary = "Check-in attendee", description = "Register an attendee's check-in at the event")
    public ResponseEntity<Void> registerCheckIn(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
        attendeeService.checkInAttendee(attendeeId);

        var uri = uriComponentsBuilder.path("/v1/attendees/{attendeesId}/badge").buildAndExpand(attendeeId).toUri();

        return ResponseEntity.created(uri).build();
    }
}