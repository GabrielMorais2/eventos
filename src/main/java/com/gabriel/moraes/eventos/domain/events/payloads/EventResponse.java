package com.gabriel.moraes.eventos.domain.events.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {
    String id;
    String title;
    String details;
    Integer maximumAttendees;
}