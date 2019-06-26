package com.cep.corporateeventplanner.service;

import com.cep.corporateeventplanner.model.Event;
import com.cep.corporateeventplanner.model.User;

import java.util.List;

public interface EventService
{

    List<Event> findAll();

    Event findById(long id);

    Event findByName(String name);

    Event create(Event event);

    void updateEvent(Event event, long id);

    void deleteEvent(long id);

    void addUserToEvent(User user, Event event);

}