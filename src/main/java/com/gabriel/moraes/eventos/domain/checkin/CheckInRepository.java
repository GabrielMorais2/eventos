package com.gabriel.moraes.eventos.domain.checkin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {
    Optional<CheckIn> findByAttendeeId(String attendeeId);
}