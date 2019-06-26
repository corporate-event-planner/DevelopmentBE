package com.cep.corporateeventplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@ApiModel(value = "Task", description = "The task entity")
@Entity
public class Task {
    @ApiModelProperty(name = "Task ID", value = "The Primary Key ID for the Task", required = true , example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long taskid;

    @ApiModelProperty(name = "Task Name", value = "The name of the task", required = true , example = "Make Reservations")
    @Column(nullable = false)
    private String name;

    @ApiModelProperty(name = "Task Description", value = "The description of the task", example = "Reserve 50 rooms at the Mariott hotel on Main")
    private String description;

    @ApiModelProperty(name = "Assigned User", value = "The name of the person assigned to carry out the task", example = "John Smith")
    private String assigned;

    @ApiModelProperty(name = "Completed", value = "The boolean representation of whether the task has been completed or not", example = "false")
    private boolean completed;

    @ApiModelProperty(name = "Duedate", value = "The date at which the task must be completed", example = "8-19-2019")
    private String duedate;

    @ApiModelProperty(name = "Category", value = "The category that the task falls under", example = "Service")
    private String category;

    @ManyToOne
    @JoinColumn(name = "eventid")
    @JsonIgnoreProperties(value = "tasklist")
    private Event event;

    @ApiModelProperty(name = "Purchase", value = "The purchase details for an event that involves buying something or paying an outside company")
    @OneToMany(mappedBy = "task", orphanRemoval = true)
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