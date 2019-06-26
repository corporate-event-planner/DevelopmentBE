package com.cep.corporateeventplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Event", description = "The Event entity")
@Entity
public class Event {

    @ApiModelProperty(name = "Event ID", value = "The Primary Key ID for the event", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long eventid;

    @ApiModelProperty(name = "Event Name", value = "The name of the Event", required = true, example = "Teambuilding Retreat")
    @Column(nullable = false)
    private String name;

    @ApiModelProperty(name = "Event Description", value = "The Description of the event", required = false, example = "IT team going on a team building retreat in Hawaii for 2 weeks")
    private String description;

    @ApiModelProperty(name = "Event Date", value = "The date of the event", required = true, example = "8-22-2019")
    @Column(nullable = false)
    private String date;

    @ApiModelProperty(name = "Event Budget", value = "The budget for the event", required = false, example = "$10,000")
    private String budget;

    @ApiModelProperty(name = "Company Name", value = "The name of the company hosting the event", required = false, example = "DevelopersAnonymous")
    private String companyname;

    @ApiModelProperty(name = "Task List", value = "The tasks associated with preparing for the event")
    @OneToMany(mappedBy = "event", orphanRemoval = true)
    @JsonIgnoreProperties(value = "event")
    List<Task> tasklist = new ArrayList<>();

    @ApiModelProperty(name = "Users List", value = "The users attending the event and/or associated with setting the event up")
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("event")
    List<UserEvents> userList = new ArrayList<>();

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

    public Event(String name, String description, String date, String budget, String companyname, List<Task> tasklist, List<UserEvents> userList) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.budget = budget;
        this.companyname = companyname;
        this.tasklist = tasklist;
        this.userList = userList;
    }

    public List<UserEvents> getUserList() {
        return userList;
    }

    public void setUserList(List<UserEvents> userList) {
        this.userList = userList;
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

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getBudget()
    {
        return budget;
    }

    public void setBudget(String budget)
    {
        this.budget = budget;
    }

    public String getCompanyname()
    {
        return companyname;
    }

    public void setCompanyname(String companyname)
    {
        this.companyname = companyname;
    }

    public List<Task> getTasklist()
    {
        return tasklist;
    }

    public void setTasklist(List<Task> tasklist)
    {
        this.tasklist = tasklist;
    }
}