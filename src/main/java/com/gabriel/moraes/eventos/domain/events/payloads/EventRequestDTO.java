package com.gabriel.moraes.eventos.domain.events.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRequestDTO {
    @NotBlank String title;
    @NotBlank String details;
    @Positive Integer maximumAttendees;
}