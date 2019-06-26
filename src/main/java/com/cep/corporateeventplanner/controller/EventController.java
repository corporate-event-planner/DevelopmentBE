package com.cep.corporateeventplanner.controller;

import com.cep.corporateeventplanner.model.*;
import com.cep.corporateeventplanner.service.EventService;
import com.cep.corporateeventplanner.service.TaskService;
import com.cep.corporateeventplanner.service.UserService;
import io.swagger.annotations.*;
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

    @ApiOperation(value = "Return all Events", response = Event.class, responseContainer = "List")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query",
                                                 value = "Results page you want to retrieve (0..N)"),
                               @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                                                 value = "Number of records per page."),
                               @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                                                 value = "Sorting criteria in the format: property(,asc|desc). " +
                                                         "Default sort order is ascending. " +
                                                         "Multiple sort criteria are supported.")})
    @GetMapping(value = "/events/all", produces = {"application/json"})
    public ResponseEntity<?> getAllEvents(){
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Return event with given event ID", response = Event.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully found event", response = Event.class),
            @ApiResponse(code = 404, message = "Could not find event", response = ErrorDetail.class)
    })
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

    @ApiOperation(value = "Create a new event", notes = "URL for new student will be in location header", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created event", response = void.class),
            @ApiResponse(code = 500, message = "Failed to create event", response = ErrorDetail.class)
    })
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

    @ApiOperation(value = "Update a current event", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully updated event", response = void.class),
            @ApiResponse(code = 500, message = "Failed to update event", response = ErrorDetail.class)
    })
    @PutMapping(value = "/events/edit/{eventid}", produces = {"application/json"})
    public ResponseEntity<?> updateEvent(@PathVariable long eventid, @RequestBody Event event, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        Event currentEvent = eventService.findById(eventid);
        if (checkUserForEvent(user, currentEvent)) {
            event.setEventid(eventid);
            if (event.getUserEvents() != null && event.getUserEvents().size() > 0){
                for (UserEvents events: event.getUserEvents()){
                    if (events.getUserE().getUsername() != null){
                        System.out.println(events.getUserE().getUsername());
                        events.setUserE(userService.findByUsername(events.getUserE().getUsername()));
                        System.out.println(events.getUserE().getUserid());
                    }else if (events.getUserE().getUserid() != 0){
                        events.setUserE(userService.findUserById(events.getUserE().getUserid()));
                    }
                    events.setEventU(currentEvent);
                }
            }
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

    @ApiOperation(value = "Delete a current event", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Succesfully deleted event", response = void.class),
            @ApiResponse(code = 500, message = "Failed to delete event", response = ErrorDetail.class)
    })
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