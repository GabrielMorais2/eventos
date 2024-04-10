package com.gabriel.moraes.eventos.domain.attendee.exception;

public class AttendeeNotFoundException extends RuntimeException{
    public AttendeeNotFoundException(String message){
        super(message);
    }
}
