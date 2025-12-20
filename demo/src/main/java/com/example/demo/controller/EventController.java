package com.example.demo.controller;
import com.example.demo.model.Event;
import com.example.demo.service.eventService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/events")

public class EventController {
    
    private final eventService EventService;

    public EventController(eventService event){
        this.EventService = event;
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents(){
        return ResponseEntity.ok(EventService.getAllEvents());
    }

    //id event, if exists return it else return 404
    
}
