package com.gabriel.moraes.eventos.domain.attendee.payloads;

import java.util.List;

public record AttendeesListResponseDTO(List<AttendeeDetails> attendees) {

}