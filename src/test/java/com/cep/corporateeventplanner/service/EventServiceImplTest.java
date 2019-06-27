package com.cep.corporateeventplanner.service;

import com.cep.corporateeventplanner.CorporateeventplannerApplication;
import com.cep.corporateeventplanner.model.Event;
import com.cep.corporateeventplanner.model.User;
import com.cep.corporateeventplanner.model.UserEvents;
import com.cep.corporateeventplanner.repo.EventRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CorporateeventplannerApplication.class)
public class EventServiceImplTest
{
    @Autowired
    EventService eventService;

    @Autowired
    EventRepository eventRepository;


    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void findAll()
    {
        assertEquals(1, eventService.findAll().size());
    }

    @Test
    public void findById()
    {
        assertEquals("Company A", eventService.findById(8).getCompanyname());
    }

    @Test
    public void findByName()
    {
        assertEquals("Teambuilding", eventService.findByName("Teambuilding"));
    }

    @Test
    public void create()
    {
        Event newEvent = new Event();
        newEvent.setName("Christmas Party");
        newEvent.setBudget("20000");
        eventService.create(newEvent);

        assertEquals(2, eventService.findAll().size());
    }

    @Test
    public void updateEvent()
    { ;
    }

    @Test
    public void deleteEvent()
    {
        eventService.deleteEvent(8);
        assertEquals(0, eventService.findAll().size());
    }

    @Test
    public void addUserToEvent()
    {
        User newUser = new User();
        newUser.setUsername("Paul");
        newUser.setCompanyname("Company A");
        assertEquals(5, eventService.findAll().size());
    }
}