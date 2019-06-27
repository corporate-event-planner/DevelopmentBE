package com.cep.corporateeventplanner.service;

import com.cep.corporateeventplanner.CorporateeventplannerApplication;
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
@SpringBootTest(classes = CorporateeventplannerApplication.class)
public class UserServiceImplTest
{
    @Autowired
    UserService userService;

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
        assertEquals(4, userService.findAll().size());
    }

    @Test
    public void findUserById()
    {
        assertEquals("JakeTheDude", userService.findUserById(2).getUsername());
    }

    @Test
    public void delete()
    {
        userService.delete(2);
        assertEquals(3, userService.findAll().size());
    }

    @Test
    public void findByUsername()
    {
        assertEquals("JakeTheDude", userService.findByUsername("JakeTheDude").getUsername());
    }
}