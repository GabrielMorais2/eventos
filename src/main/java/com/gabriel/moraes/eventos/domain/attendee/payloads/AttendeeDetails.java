package com.gabriel.moraes.eventos.domain.attendee.payloads;

import java.time.LocalDateTime;

public record AttendeeDetails(String id, String name, String email, LocalDateTime createdAt, LocalDateTime checkedInAt) {

}