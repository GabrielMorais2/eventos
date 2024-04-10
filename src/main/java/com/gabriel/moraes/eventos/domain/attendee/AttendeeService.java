package com.gabriel.moraes.eventos.domain.attendee;

import com.gabriel.moraes.eventos.domain.attendee.payloads.AttendeeBadgeResponseDTO;
import com.gabriel.moraes.eventos.domain.attendee.payloads.AttendeesListResponseDTO;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


public interface AttendeeService {
    List<Attendee> getAllAttendeesFromEvent(String eventId);

    void verifyAttendeeSubscription(String email, String eventId);
    AttendeesListResponseDTO getEventsAttendee(String eventId);

    Attendee registerAttendee(Attendee newAttendee);

    void checkInAttendee(String attendeeId);

    AttendeeBadgeResponseDTO getAttendeeBadge(String attendeeId, UriComponentsBuilder uriComponentsBuilder);

}