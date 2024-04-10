package com.gabriel.moraes.eventos.domain.attendee.payloads;

import java.time.LocalDateTime;

public record AttendeeDetails(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime checkedInAt) {

}