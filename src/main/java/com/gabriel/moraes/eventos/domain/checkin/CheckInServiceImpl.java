package com.gabriel.moraes.eventos.domain.checkin;

import com.gabriel.moraes.eventos.domain.attendee.Attendee;
import com.gabriel.moraes.eventos.domain.checkin.exceptions.CheckInAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckInServiceImpl implements CheckInService{
    private final CheckInRepository checkinRepository;

    public void registerCheckIn (Attendee attendee){
        this.verifyCheckInExists(attendee.getId());

        CheckIn newCheckIn = new CheckIn();
        newCheckIn.setAttendee(attendee);
        newCheckIn.setCreatedAt(LocalDateTime.now());

        this.checkinRepository.save(newCheckIn);
    }
    private void verifyCheckInExists(String attendeeId){
        Optional<CheckIn> isCheckedIn = this.getCheckIn(attendeeId);
        if (isCheckedIn.isPresent()) throw new CheckInAlreadyExistsException("Attendee already checked in");

    }

    public Optional<CheckIn> getCheckIn(String attendeeId){
        return checkinRepository.findByAttendeeId(attendeeId);
    }
}