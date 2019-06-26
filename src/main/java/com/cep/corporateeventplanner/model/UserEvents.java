package com.cep.corporateeventplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    private User user;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "eventid")
    @JsonIgnoreProperties("userList")
    private Event event;

    public UserEvents() {
    }

    public UserEvents(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEvents)) return false;
        UserEvents that = (UserEvents) o;
        return getUser().equals(that.getUser()) &&
                getEvent().equals(that.getEvent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getEvent());
    }
}