package com.gabriel.moraes.eventos.domain.events.exceptions;

public class EventNotFoundException extends RuntimeException{
    public EventNotFoundException(String message){
        super(message);
    }
}