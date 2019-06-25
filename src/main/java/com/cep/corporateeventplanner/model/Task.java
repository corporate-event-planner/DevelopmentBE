package com.cep.corporateeventplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long taskid;

    @Column(nullable = false)
    private String name;

    private String description;

    private String assigned;

    private boolean completed;

    private String duedate;

    private String category;

    @ManyToOne
    @JoinColumn(name = "eventid")
    @JsonIgnoreProperties(value = "tasklist")
    private Event event;

    @OneToMany(mappedBy = "task")
    @JsonIgnoreProperties(value = "task")
    private List<Purchase> purchase;

    public Task() {
    }

    public Task(String name, String description, String assigned, boolean completed, String duedate, String category, Event event) {
        this.name = name;
        this.description = description;
        this.assigned = assigned;
        this.completed = completed;
        this.duedate = duedate;
        this.category = category;
        this.event = event;
    }

    public Task(String name, String description, String assigned, boolean completed, String duedate, String category, Event event, List<Purchase> purchase) {
        this.name = name;
        this.description = description;
        this.assigned = assigned;
        this.completed = completed;
        this.duedate = duedate;
        this.category = category;
        this.event = event;
        this.purchase = purchase;
    }

    public long getTaskid() {
        return taskid;
    }

    public void setTaskid(long taskid) {
        this.taskid = taskid;
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

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Purchase> getPurchase() {
        return purchase;
    }

    public void setPurchase(List<Purchase> purchase) {
        this.purchase = purchase;
    }
}