package com.cep.corporateeventplanner.repo;

import com.cep.corporateeventplanner.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}