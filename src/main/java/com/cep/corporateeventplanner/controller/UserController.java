package com.cep.corporateeventplanner.controller;

import com.cep.corporateeventplanner.model.Event;
import com.cep.corporateeventplanner.model.User;
import com.cep.corporateeventplanner.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/users")
public class UserController
{
    private static final Logger logger = LoggerFactory.getLogger(User.class);



    @Autowired
    private UserService userService;


    @GetMapping(value = "/users", produces = {"application/json"})
    public ResponseEntity<?> getAllUsers()
    {

        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}", produces = {"application/json"})
    public ResponseEntity<?> getUsersById(@PathVariable long id, Authentication authentication)
    {
        Event event = (Event) authentication.getPrincipal();
        User user = userService.findUserById(id);
        if (checkUserForEvent(user, event ))
        {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else
        {
            throw new AuthorizationServiceException("User not authorized");
        }
    }

    private boolean checkUserForEvent(User user, Event event)
    {
        long userid = user.getUserid();
        for (User eventUser: event.getUserlist())
        {
            if (eventUser.getUserid() == userid)
            {
                return true;
            }
        }
        return false;
    }



    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/getusername", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> getCurrentUserName(HttpServletRequest request, Authentication authentication)
    {
        logger.trace(request.getRequestURI() + " accessed");

        return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/user", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> getUser(HttpServletRequest request, @Valid
    @RequestBody User user) throws URISyntaxException
    {
        logger.trace(request.getRequestURI() + " accessed");

        user =  userService.save(user);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(user.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping(value = "/user/{id}")
    public ResponseEntity<?> updateUser(HttpServletRequest request, @RequestBody User updateUser, @PathVariable long id)
    {
        logger.trace(request.getRequestURI() + " accessed");

        userService.update(updateUser, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(HttpServletRequest request, @PathVariable long id)
    {
        logger.trace(request.getRequestURI() + " accessed");

        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}