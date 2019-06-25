package com.cep.corporateeventplanner.controller;

import com.cep.corporateeventplanner.model.Event;
import com.cep.corporateeventplanner.model.User;
import com.cep.corporateeventplanner.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping(value = "/events/all", produces = {"application/json"})
    public ResponseEntity<?> getAllEvents(){
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/events/{id}", produces = {"application/json"})
    public ResponseEntity<?> getEventById(@PathVariable long id, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Event event = eventService.findById(id);
        if (checkUserForEvent(user, event)){
            return new ResponseEntity<>(event, HttpStatus.OK);
        }else{
            throw new AuthorizationServiceException("User not authorized");
        }
    }


    private boolean checkUserForEvent(User user, Event event){
        long userid = user.getUserid();
        for (User eventUser: event.getUserlist()){
            if (eventUser.getUserid() == userid){
                return true;
            }
        }
        return false;
    }
}
