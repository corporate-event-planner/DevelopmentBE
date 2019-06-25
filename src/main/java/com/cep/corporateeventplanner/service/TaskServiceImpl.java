package com.cep.corporateeventplanner.service;

import com.cep.corporateeventplanner.model.Event;
import com.cep.corporateeventplanner.model.Task;
import com.cep.corporateeventplanner.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "taskService")
public class TaskServiceImpl implements TaskService
{
    @Autowired
    TaskRepository taskrepo;

    @Override
    public List<Task> findAll()
    {
        List<Task> list = new ArrayList<>();
        taskrepo.findAll().iterator().forEachRemaining(list::add);

        return list;
    }

    @Override
    public List<Task> findAllByEvent(long eventId)
    {
        return null;
    }

    @Override
    public Task findById(long id)
    {
        return taskrepo.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void createNewTask(Task task)
    {
        taskrepo.save(task);
    }

    @Override
    public void updateTask(Task task, long id)
    {
        Task currentTask = taskrepo.findById(id).orElseThrow(EntityNotFoundException::new);
        if (task.getName() != null)
        {
            currentTask.setName(task.getName());
        }
        if (task.getAssigned() !=null)
        {
            currentTask.setAssigned(task.getAssigned());
        }
        if (task.getCategory() != null)
        {
            currentTask.setCategory(task.getCategory());
        }
        if (task.getDescription() != null)
        {
            currentTask.setDescription(task.getDescription());
        }
        if (currentTask.getDuedate() != null)
        {
            currentTask.setDuedate(task.getDuedate());
        }

        taskrepo.save(currentTask);

    }

    @Override
    public void setTaskCompleted(long id)
    {
    }

    @Override
    public void deleteTask(long id)
    {
        if( taskrepo.findById(id).isPresent())
        {
            taskrepo.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }
}
