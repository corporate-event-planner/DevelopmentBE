package com.cep.corporateeventplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "userevents")
public class UserEvents implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("userEvents")
    private User userE;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "eventid")
    @JsonIgnoreProperties("userEvents")
    private Event eventU;

    public UserEvents() {
    }

    public UserEvents(User userE, Event eventU) {
        this.userE = userE;
        this.eventU = eventU;
    }

    public User getUserE() {
        return userE;
    }

    public void setUserE(User userE) {
        this.userE = userE;
    }

    public Event getEventU() {
        return eventU;
    }

    public void setEventU(Event eventU) {
        this.eventU = eventU;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEvents)) return false;
        UserEvents that = (UserEvents) o;
        return getUserE().equals(that.getUserE()) &&
                getEventU().equals(that.getEventU());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserE(), getEventU());
    }
}