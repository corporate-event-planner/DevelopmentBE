package com.cep.corporateeventplanner.service;

import com.cep.corporateeventplanner.model.Event;
import com.cep.corporateeventplanner.model.Task;
import com.cep.corporateeventplanner.model.User;
import com.cep.corporateeventplanner.model.UserEvents;
import com.cep.corporateeventplanner.repo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "eventService")
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository repo;

    @Override
    public List<Event> findAll() {
        List<Event> eventList = new ArrayList<>();
        repo.findAll().iterator().forEachRemaining(eventList::add);
        return eventList;
    }

    @Override
    public Event findById(long id) {
        return repo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Event findByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public Event create(Event event) {
        repo.save(event);
        return event;
    }

    @Override
    public void updateEvent(Event event, long id) {
        Event currentEvent = repo.findById(id).orElseThrow(EntityNotFoundException::new);
        if (event.getBudget() != null){
            currentEvent.setBudget(event.getBudget());
        }
        if (event.getCompanyname() != null){
            currentEvent.setCompanyname(event.getCompanyname());
        }
        if (event.getDate() != null){
            currentEvent.setDate(event.getDate());
        }
        if (event.getDescription() != null){
            currentEvent.setDescription(event.getDescription());
        }
        if(event.getName() != null){
            currentEvent.setName(event.getName());
        }
        if(event.getTasklist() != null && event.getTasklist().size() > 0){
            for (Task task: event.getTasklist()){
                currentEvent.getTasklist().add(task);
            }
        }
        if(event.getUserEvents() != null && event.getUserEvents().size() > 0){
            for (UserEvents user: event.getUserEvents()){
                currentEvent.getUserEvents().add(new UserEvents(user.getUserE(), currentEvent));
            }
        }
        repo.save(currentEvent);

    }

    @Override
    public void deleteEvent(long id) {
        if (repo.findById(id).isPresent()){
            repo.deleteById(id);
        }else{
            throw new EntityNotFoundException();
        }
    }
}