package com.cep.corporateeventplanner.controller;

import com.cep.corporateeventplanner.model.*;
import com.cep.corporateeventplanner.service.EventService;
import com.cep.corporateeventplanner.service.TaskService;
import com.cep.corporateeventplanner.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EventController.class, secure = false)
//@ContextConfiguration( classes = CorporateeventplannerApplication.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EventService eventService;

    @MockBean
    UserService userService;

    @MockBean
    TaskService taskService;

    private List<Event> eventList;

    @Before
    public void setUp() {
        eventList = new ArrayList<>();

        List<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), new Role("user")));

        User user1 = new User("JakeTheDude", "TurkeyLeg", users);
        user1.setRole("Backend B.A.");
        user1.setCompanyname("DevelopersAnonymous");
        user1.setEmail("JakeTheDude@Email.com");

        Event event1 = new Event();
        event1.setCompanyname("Company A");
        event1.setDate("8-23-2019");
        event1.setBudget("$10,000");
        event1.setName("Teambuilding Trip");
        event1.setDescription("Take the IT department on a teambuilding getaway in Hawaii");
        event1.getUserList().add(new UserEvents(user1, event1));

/*        Task task1 = new Task("Reservations","Make Hotel Reservations", "John", false, "8-1-2019","Service", event1);
        Task task2 = new Task("RSVP", "Have all employees either RSVP or opt out", "Michelle", false, "7-15-2019", "Task", event1);
        Purchase purchase1 = new Purchase("Reserve Hotel Rooms", "Mariott Hotel", "Judy", "judyisawesome@email.com", "$3,000", 0, task1);
        task1.setPurchase(new ArrayList(Arrays.asList(purchase1)));
        event1.getTasklist().add(task1);
        event1.getTasklist().add(task2);*/

        eventList.add(event1);
    }


    @Test
    public void getAllEvents() throws Exception {
        String apiUrl = "/events/all";

        Mockito.when(eventService.findAll()).thenReturn((ArrayList<Event>) eventList);

        RequestBuilder builder = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(builder).andReturn();
        String response = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String expected = mapper.writeValueAsString(eventList);

        assertEquals(expected, response);
    }

    @Test
    public void getEventById() {
    }

    @Test
    public void postNewEvent() throws Exception {
        String apiUrl = "/events/new";
        Event newEvent = new Event();
        newEvent.setName("Turkey");
        newEvent.setDescription("The Turkish Event");
        newEvent.setDate("8-8-2018");
        newEvent.setCompanyname("Company T");
        newEvent.setBudget("$1,000,000");
        newEvent.setEventid(10);
        newEvent.setTasklist(new ArrayList<>());
        newEvent.setUserList(new ArrayList<>());
        Mockito.when(eventService.create(newEvent)).thenReturn(((Event) newEvent));

        ObjectMapper inputMapper = new ObjectMapper();
        String inputString;
        inputString = inputMapper.writeValueAsString(newEvent);

        mockMvc.perform(MockMvcRequestBuilders.post(apiUrl).content(inputString)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

    }

    @Test
    public void updateEvent() {
    }

    @Test
    public void deleteEvent() {
    }

}