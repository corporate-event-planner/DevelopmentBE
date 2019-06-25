package com.cep.corporateeventplanner.service;

import com.cep.corporateeventplanner.model.Task;

import java.util.List;

public interface TaskService
{

    List<Task> findAll();

    List<Task> findAllByEvent(long eventId);

    Task findById(long id);

    void createNewTask(Task task);

    void updateTask(Task task, long id);

    void setTaskCompleted(long id);

    void deleteTask(long id);
}