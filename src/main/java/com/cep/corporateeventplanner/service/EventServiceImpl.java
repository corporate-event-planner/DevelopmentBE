package com.cep.corporateeventplanner.service;

import com.cep.corporateeventplanner.model.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "eventService")
public class EventServiceImpl implements EventService {
    @Override
    public List<Event> findAll() {asdfas
        return null;
    }

    @Override
    public Event findById(long id) {
        return null;
    }

    @Override
    public Event findByName(String name) {
        return null;
    }

    @Override
    public Event create(Event event) {
        return null;
    }

    @Override
    public void updateEvent(Event event, long id) {

    }

    @Override
    public void deleteEvent(long id) {

    }
}
