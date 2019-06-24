package com.cep.corporateeventplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long eventid;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private String date;

    private String budget;

    private String companyname;

    @OneToMany(mappedBy = "event")
    @JsonIgnoreProperties(value = "event")
    List<Task> tasklist = new ArrayList<>();

    @ManyToMany(mappedBy = "eventlist")
    @JsonIgnoreProperties("eventlist")
    List<User> userlist = new ArrayList<>();

    public Event() {
    }

    public Event(String name, String description, String date, String budget, String companyname, List<Task> tasklist) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.budget = budget;
        this.companyname = companyname;
        this.tasklist = tasklist;
    }

    public Event(String name, String description, String date, String budget, String companyname) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.budget = budget;
        this.companyname = companyname;
    }

    public Event(String name, String description, String date, String budget, String companyname, List<Task> tasklist, List<User> userlist) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.budget = budget;
        this.companyname = companyname;
        this.tasklist = tasklist;
        this.userlist = userlist;
    }

    public List<User> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<User> userlist) {
        this.userlist = userlist;
    }

    public long getEventid() {
        return eventid;
    }

    public void setEventid(long eventid) {
        this.eventid = eventid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public List<Task> getTasklist() {
        return tasklist;
    }

    public void setTasklist(List<Task> tasklist) {
        this.tasklist = tasklist;
    }
}
