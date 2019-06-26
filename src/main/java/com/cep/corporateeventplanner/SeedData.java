package com.cep.corporateeventplanner;

import com.cep.corporateeventplanner.model.*;
import com.cep.corporateeventplanner.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class SeedData implements CommandLineRunner {


    @Autowired
    EventRepository eventRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PurchaseRepository purchaseRepository;
/*    @Autowired
    EventService eventService;

    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PurchaseService purchaseService;*/

    @Override
    public void run(String... args) throws Exception {

        Role r1 = new Role("USER");
        roleRepository.save(r1);

        List<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r1));

        User user1 = new User("JakeTheDude", "TurkeyLeg", users);
        user1.setRole("Backend B.A.");
        user1.setCompanyname("DevelopersAnonymous");
        user1.setEmail("JakeTheDude@Email.com");

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r1));
        User user2 = new User("SamIAm", "password", users);
        user2.setRole("Frontend Ninja");
        user2.setCompanyname("DevelopersAnonymous");
        user2.setEmail("SamIAm@Email.com");

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r1));
        User user3 = new User("AlejandroTheAnimal", "password", users);
        user3.setRole("Frontend Ninja");
        user3.setCompanyname("DevelopersAnonymous");
        user3.setEmail("AlejandroTheAnimal@Email.com");

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r1));
        User user4 = new User("HugoTheGiant", "password", users);
        user4.setRole("Backend B.A.");
        user4.setCompanyname("DevelopersAnonymous");
        user4.setEmail("HugoTheGiant@Email.com");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);


        Event event1 = new Event();
        event1.setCompanyname("Company A");
        event1.setDate("8-23-2019");
        event1.setBudget("$10,000");
        event1.setName("Teambuilding Trip");
        event1.setDescription("Take the IT department on a teambuilding getaway in Hawaii");
//        event1.setUserList(new ArrayList<>(Arrays.asList(new UserEvents(user1, event1))));
        event1.getUserList().add(new UserEvents(user1, event1));


        Task task1 = new Task("Reservations","Make Hotel Reservations", "John", false, "8-1-2019","Service", event1);
//        event1.getTasklist().add(task1);
        Task task2 = new Task("RSVP", "Have all employees either RSVP or opt out", "Michelle", false, "7-15-2019", "Task", event1);
        //event1.getTasklist().add(task2);
        //event1.getUserList().add(new UserEvents(user1, event1));
        //eventService.create(event1);
        Purchase purchase1 = new Purchase("Reserve Hotel Rooms", "Mariott Hotel", "Judy", "judyisawesome@email.com", "$3,000", 0, task1);




        eventRepository.save(event1);
        taskRepository.save(task1);
        taskRepository.save(task2);
        purchaseRepository.save(purchase1);
        /*eventService.create(event1);
        taskService.createNewTask(task1);
        taskService.createNewTask(task2);
        purchaseService.create(purchase1);
*/


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