package com.example.demo.service;
import com.example.demo.model.Event;

import java.util.ArrayList;
import java.util.List;

public class eventService {
    private List<Event> events = new ArrayList<>();

    public List<Event> getAllEvents(){
        return events;
    }

    public Event getEventByID(int id){
        return events.stream()
            .filter(e->e.getID()==id)
            .findFirst()
            .orElse(null);
    }

    public Event addEvent(Event event){
        events.add(event);
        return event;
    }

    public boolean deleteEvent(int id){
        return events.removeIf(e->e.getID()==id);
    }

    
}
