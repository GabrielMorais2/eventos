package com.gabriel.moraes.eventos.domain.attendee.payloads;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeRequestDTO {
    @NotBlank String name;
    @NotBlank String email;
}
