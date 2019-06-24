package com.cep.corporateeventplanner;

import com.cep.corporateeventplanner.model.Event;
import com.cep.corporateeventplanner.model.Purchase;
import com.cep.corporateeventplanner.model.Task;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class SeedData implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        Event event1 = new Event();
        event1.setCompanyname("Company A");
        event1.setDate("8-23-2019");
        event1.setBudget("$10,000");
        event1.setName("Teambuilding Trip");
        event1.setDescription("Take the sales team on a teambuilding getaway in Hawaii");
        List<Task> taskList1 = new ArrayList<>();
        taskList1.add(new Task("Reservations","Make Hotel Reservations", "John", false, "8-1-2019", "Service", event1,
                new Purchase("Reserve Hotel Rooms", "Mariott Hotel", "Judy", "judyisawesome@email.com", "$3,000")));
        taskList1.add(new Task("RSVP", "Have all employees either RSVP or opt out", "Michelle", false, "7-15-2019", "Task", event1));
        event1.setTasklist(taskList1);

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