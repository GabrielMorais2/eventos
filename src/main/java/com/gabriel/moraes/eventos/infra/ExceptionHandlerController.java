package com.gabriel.moraes.eventos.infra;

import com.gabriel.moraes.eventos.domain.attendee.exception.AttendeeAlreadyRegisteredException;
import com.gabriel.moraes.eventos.domain.attendee.exception.AttendeeNotFoundException;
import com.gabriel.moraes.eventos.domain.checkin.exceptions.CheckInAlreadyExistsException;
import com.gabriel.moraes.eventos.domain.events.exceptions.EventNotFoundException;
import com.gabriel.moraes.eventos.domain.user.exceptions.EmailAlreadyExistsException;
import com.gabriel.moraes.eventos.infra.validationerror.ValidationError;
import com.gabriel.moraes.eventos.infra.validationerror.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerController {

    private final LocalDateTime timestamp = LocalDateTime.now();

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception ex) {
        ErrorResponse message = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), timestamp,
                ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorResponse> EventNotFoundException(EventNotFoundException ex) {
        ErrorResponse message = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                timestamp,
                ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AttendeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> AttendeeNotFoundException(AttendeeNotFoundException ex) {
        ErrorResponse message = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                timestamp,
                ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AttendeeAlreadyRegisteredException.class)
    public ResponseEntity<ErrorResponse> AttendeeAlreadyRegisteredException(AttendeeAlreadyRegisteredException ex) {
        ErrorResponse message = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                timestamp,
                ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CheckInAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> CheckInAlreadyExistsException(CheckInAlreadyExistsException ex) {
        ErrorResponse message = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                timestamp,
                ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> emailAlreadyExistsException(EmailAlreadyExistsException ex) {
        ErrorResponse message = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                timestamp,
                ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> badCredentialsException(BadCredentialsException ex) {
        ErrorResponse message = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                timestamp,
                ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ValidationError> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ValidationError(fieldError.getDefaultMessage(), fieldError.getField()))
                .collect(Collectors.toList());

        ValidationErrorResponse message = new ValidationErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                timestamp,
                "Validation failed: Invalid argument(s) in the request",
                validationErrors);

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }


}