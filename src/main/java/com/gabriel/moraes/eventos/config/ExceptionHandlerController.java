package com.gabriel.moraes.eventos.config;

import com.gabriel.moraes.eventos.domain.events.exceptions.EventNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlerController {

    private final LocalDateTime timestamp = LocalDateTime.now();

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception ex) {
        ErrorResponse message = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                timestamp,
                ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorResponse> ProductNotFoundException(EventNotFoundException ex) {
        ErrorResponse message = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                timestamp,
                ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

}