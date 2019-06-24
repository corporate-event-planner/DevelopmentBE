package com.cep.corporateeventplanner.controller;

import com.cep.corporateeventplanner.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping(value = "/events/all", produces = {"application/json"})
    public ResponseEntity<?> getAllEvents(){
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }

}
