package com.gabriel.moraes.eventos.domain.events.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDTO {
    String id;
    String title;
    String details;
    Integer maximumAttendees;
}