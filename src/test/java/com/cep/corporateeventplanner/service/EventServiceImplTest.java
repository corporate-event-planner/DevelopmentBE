package com.cep.corporateeventplanner.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = EventServiceImpl.class)



public class EventServiceImplTest
{
    @Autowired
    EventService eventService;
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
//        assertEquals("Company A", eventService.findById(8).getCompanyname());
    }

    @Test
    public void findByName()
    {
//        assertEquals("Teambuilding", eventService.findByName("Teambuilding"));
    }

    @Test
    public void create()
    {
    }

    @Test
    public void updateEvent()
    {
    }

    @Test
    public void deleteEvent()
    {
    }

    @Test
    public void addUserToEvent()
    {
    }
}