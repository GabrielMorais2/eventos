package com.gabriel.moraes.eventos.domain.events.exceptions;

public class EventIsFullException extends RuntimeException{
    public EventIsFullException(String message){
        super(message);
    }
}