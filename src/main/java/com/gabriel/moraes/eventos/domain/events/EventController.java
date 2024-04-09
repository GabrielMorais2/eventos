package com.gabriel.moraes.eventos.domain.events;

import com.gabriel.moraes.eventos.domain.events.payloads.EventRequest;
import com.gabriel.moraes.eventos.domain.events.payloads.EventResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable String id){
        return new ResponseEntity<>(eventService.getEventById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<EventResponse> createProduct(@Valid @RequestBody EventRequest eventRequest){
        return new ResponseEntity<>(eventService.createEvent(eventRequest), HttpStatus.CREATED);
    }




}
