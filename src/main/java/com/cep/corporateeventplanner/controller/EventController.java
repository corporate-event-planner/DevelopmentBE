package com.cep.corporateeventplanner.controller;

import com.cep.corporateeventplanner.model.Event;
import com.cep.corporateeventplanner.model.Task;
import com.cep.corporateeventplanner.model.User;
import com.cep.corporateeventplanner.model.UserEvents;
import com.cep.corporateeventplanner.service.EventService;
import com.cep.corporateeventplanner.service.TaskService;
import com.cep.corporateeventplanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController
{

    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @GetMapping(value = "/events/all", produces = {"application/json"})
    public ResponseEntity<?> getAllEvents(){
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/events/{eventid}", produces = {"application/json"})
    public ResponseEntity<?> getEventById(@PathVariable long eventid, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Event event = eventService.findById(eventid);
        User user = userService.findByUsername(userDetails.getUsername());
//        return new ResponseEntity<>(event, HttpStatus.OK);
        if (checkUserForEvent(user, event)){
            return new ResponseEntity<>(event, HttpStatus.OK);
        }else{
            throw new AuthorizationServiceException("User not authorized");
        }
    }

    @PostMapping(value = "/events/new")
    public ResponseEntity<?> postNewEvent(@RequestBody Event event, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        event.getUserEvents().add(new UserEvents(user, event));
        eventService.create(event);
        for (Task task: event.getTasklist()){
            task.setEvent(event);
            taskService.createNewTask(task);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/events/edit/{eventid}", produces = {"application/json"})
    public ResponseEntity<?> updateEvent(@PathVariable long eventid, @RequestBody Event event, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        event.setEventid(eventid);
        if (checkUserForEvent(user, event)) {
            eventService.updateEvent(event, eventid);
            for (Task task : event.getTasklist()) {
                task.setEvent(event);
                taskService.createNewTask(task);
            }
            return new ResponseEntity<>(event, HttpStatus.OK);
        }else{
            throw new AuthorizationServiceException("User not authorized");
        }
    }

    @DeleteMapping(value = "/events/delete/{eventid}")
    public ResponseEntity<?> deleteEvent(@PathVariable long eventid, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        Event event = eventService.findById(eventid);
        if (checkUserForEvent(user, event)) {
            eventService.deleteEvent(eventid);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            throw new AuthorizationServiceException("User not authorized");
        }
    }


    private boolean checkUserForEvent(User user, Event event){
        long userid = user.getUserid();
        for (UserEvents eventUser: event.getUserEvents()){
            if (eventUser.getUserE().getUserid() == userid){
                return true;
            }
        }
        return false;
    }
}