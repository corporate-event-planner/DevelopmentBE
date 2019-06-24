package com.cep.corporateeventplanner.repo;

import com.cep.corporateeventplanner.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
