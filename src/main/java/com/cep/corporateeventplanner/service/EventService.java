package com.cep.corporateeventplanner.service;

import com.cep.corporateeventplanner.model.Event;

import java.util.List;

public interface EventService
{

    List<Event> findAll();

    Event findById(long id);

    Event findByName(String name);

    Event create(Event event);

    void updateEvent(Event event, long id);

    void deleteEvent(long id);

}