package com.cep.corporateeventplanner;

import com.cep.corporateeventplanner.model.*;
import com.cep.corporateeventplanner.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Transactional
@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    EventService eventService;

    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PurchaseService purchaseService;

    @Override
    public void run(String... args) throws Exception {

        Role r1 = new Role("USER");
        roleService.save(r1);

        List<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r1));
        User user1 = new User("JakeTheDude", "TurkeyLeg", users);
        //        user1.setUsername("JakeTheDude");
        //        user1.setPassword("TurkeyLeg");


        user1.setRole("Backend B.A.");
        user1.setCompanyname("DevelopersAnonymous");
        user1.setEmail("JakeTheDude@Email.com");

        //        user1.setUserRoles(users);
        //        user1.getUserRoles().add(new UserRoles(user1, r1));
        //        roleService.insertUserRoles(user1.getUserid(), r1.getRoleid());
        userService.save(user1);




        Event event1 = new Event();
        event1.setCompanyname("Company A");
        event1.setDate("8-23-2019");
        event1.setBudget("$10,000");
        event1.setName("Teambuilding Trip");
        event1.setDescription("Take the IT department on a teambuilding getaway in Hawaii");
        List<Task> taskList1 = new ArrayList<>();
        Purchase purchase1 = new Purchase("Reserve Hotel Rooms", "Mariott Hotel", "Judy", "judyisawesome@email.com", "$3,000");
        Task task1 = new Task("Reservations","Make Hotel Reservations", "John", false, "8-1-2019", "Service", event1,
                Collections.singletonList(purchase1));
        purchase1.setTask(task1);
        taskService.createNewTask(task1);
        purchaseService.create(purchase1);
        taskList1.add(task1);
        event1.getTasklist().add(task1);
        Task task2 = new Task("RSVP", "Have all employees either RSVP or opt out", "Michelle", false, "7-15-2019", "Task", event1);
        taskService.createNewTask(task2);
        event1.getTasklist().add(task2);
        event1.getUserlist().add(user1);
        eventService.create(event1);

    }
}
/*        this.name = name;
        this.description = description;
        this.assigned = assigned;
        this.completed = completed;
        this.duedate = duedate;
        this.category = category;
        this.event = event;
        this.purchase = purchase;*/

/*        this.description = description;
        this.vendorname = vendorname;
        this.pointofcontact = pointofcontact;
        this.email = email;
        this.price = price;*/