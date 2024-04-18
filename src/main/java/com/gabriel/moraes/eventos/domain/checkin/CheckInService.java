package com.gabriel.moraes.eventos.domain.checkin;

import com.gabriel.moraes.eventos.domain.attendee.Attendee;

import java.util.Optional;

public interface CheckInService {

    void registerCheckIn (Attendee attendee);
    Optional<CheckIn> getCheckIn(String attendeeId);
}