package com.cep.corporateeventplanner.repo;

import com.cep.corporateeventplanner.model.Event;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EventRepository extends CrudRepository<Event, Long>
{
    Event findByName(String name);

    @Transactional
    @Modifying
    @Query(value = "DELETE from UserEvents where eventid = :eventid")
    void deleteUserEventsByEventId(long eventid);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO UserEvents(userid, eventid) values (:userid, :eventid)", nativeQuery = true)
    void insertUserEvents(long userid, long eventid);
}