package com.cep.corporateeventplanner.service;

import com.cep.corporateeventplanner.CorporateeventplannerApplication;
import com.cep.corporateeventplanner.model.Event;
import com.cep.corporateeventplanner.model.User;
import com.cep.corporateeventplanner.model.UserEvents;
import com.cep.corporateeventplanner.repo.EventRepository;
import com.cep.corporateeventplanner.repo.UserRepository;
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

    @Autowired
    UserRepository userRepository;



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
        assertEquals("Company A", eventService.findById(6).getCompanyname());
    }

    @Test
    public void findByName()
    {
        assertEquals("Teambuilding Trip", eventService.findByName("Teambuilding Trip").getName());
    }

    @Test
    public void create()
    {
        Event newEvent = new Event();
        newEvent.setName("Christmas Party");
        newEvent.setBudget("20000");
        newEvent.setDate("8-1-10");
        eventService.create(newEvent);

        assertEquals(2, eventService.findAll().size());
    }


    @Test
    public void deleteEvent()
    {
        eventService.deleteEvent(6);
        assertEquals(0, eventService.findAll().size());
    }

}