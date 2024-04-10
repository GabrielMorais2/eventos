package com.gabriel.moraes.eventos.domain.attendee;

import com.gabriel.moraes.eventos.domain.attendee.exception.AttendeeAlreadyRegisteredException;
import com.gabriel.moraes.eventos.domain.attendee.exception.AttendeeNotFoundException;
import com.gabriel.moraes.eventos.domain.attendee.payloads.AttendeeBadgeDTO;
import com.gabriel.moraes.eventos.domain.attendee.payloads.AttendeeBadgeResponseDTO;
import com.gabriel.moraes.eventos.domain.attendee.payloads.AttendeeDetails;
import com.gabriel.moraes.eventos.domain.attendee.payloads.AttendeesListResponseDTO;
import com.gabriel.moraes.eventos.domain.checkin.CheckIn;
import com.gabriel.moraes.eventos.domain.checkin.CheckInService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {

    private final AttendeeRepository attendeeRepository;
    private final CheckInService checkInService;

    public List<Attendee> getAllAttendeesFromEvent(String eventId) {
        return attendeeRepository.findByEventId(eventId);
    }

    public void verifyAttendeeSubscription(String email, String eventId) {
        if (attendeeRepository.findByEventIdAndEmail(eventId, email).isPresent()) {
            throw new AttendeeAlreadyRegisteredException("Attendee is already registered");
        }
    }

    public AttendeesListResponseDTO getEventsAttendee(String eventId) {
        List<Attendee> attendeeList = getAllAttendeesFromEvent(eventId);

        List<AttendeeDetails> attendeeDetailsList = attendeeList.stream()
                .map(this::mapAttendeeToDetails)
                .collect(Collectors.toList());

        return new AttendeesListResponseDTO(attendeeDetailsList);
    }

    public Attendee registerAttendee(Attendee newAttendee) {
        return attendeeRepository.save(newAttendee);
    }

    public void checkInAttendee(Long attendeeId) {
        Attendee attendee = getAttendeeById(attendeeId);
        checkInService.registerCheckIn(attendee);
    }

    public AttendeeBadgeResponseDTO getAttendeeBadge(Long attendeeId, UriComponentsBuilder uriComponentsBuilder) {
        Attendee attendee = getAttendeeById(attendeeId);

        String uri = uriComponentsBuilder.path("/v1/attendees/{attendeesId}/check-in")
                .buildAndExpand(attendeeId)
                .toUriString();

        return new AttendeeBadgeResponseDTO(
                new AttendeeBadgeDTO(
                        attendee.getName(),
                        attendee.getEmail(),
                        uri,
                        attendee.getEvent().getId()
                )
        );
    }

    private AttendeeDetails mapAttendeeToDetails(Attendee attendee) {
        Optional<CheckIn> checkIn = checkInService.getCheckIn(attendee.getId());
        LocalDateTime checkedInAt = checkIn.map(CheckIn::getCreatedAt).orElse(null);
        return new AttendeeDetails(
                attendee.getId(),
                attendee.getEmail(),
                attendee.getName(),
                attendee.getCreatedAt(),
                checkedInAt
        );
    }

    private Attendee getAttendeeById(Long attendeeId) {
        return attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new AttendeeNotFoundException("Attendee not found with ID: " + attendeeId));
    }
}
