package com.cep.corporateeventplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.sql.Blob;
import java.util.ArrayList;

@Entity
@Table
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @Column(nullable = false,
            unique = true)
    private String username;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false,
            unique = true)
    private String email;

    @Column(nullable = false)
    private String companyname;

    @Column(nullable = false)
    private String role;

//    @ManyToMany
//    @JsonIgnoreProperties(value = "userlist")
//    @JoinTable(name = "userEvents", joinColumns = {@JoinColumn(name = "userid")}, inverseJoinColumns = {@JoinColumn(name = "eventid")})
//    List<Event> eventlist = new ArrayList<>();

    @Lob
    private Blob image;

    public User()
    {
    }

    public User(String username, String password, String email, String companyname, String role, Blob image)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.companyname = companyname;
        this.role = role;
        this.image = image;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getCompanyname()
    {
        return companyname;
    }

    public void setCompanyname(String companyname)
    {
        this.companyname = companyname;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public Blob getImage()
    {
        return image;
    }

    public void setImage(Blob image)
    {
        this.image = image;
    }
}
