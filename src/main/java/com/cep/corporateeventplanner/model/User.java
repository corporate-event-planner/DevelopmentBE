package com.cep.corporateeventplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "User", description = "The user entity")
@Entity
@Table
public class User
{
    @ApiModelProperty(name = "User ID", value = "The Primary Key ID for the user", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @ApiModelProperty(name = "Username", value = "The username for the user", required = true, example = "JohnTheAirGuitarSmith")
    @Column(nullable = false,
            unique = true)
    private String username;

    @ApiModelProperty(name = "Password", value = "The password of the user", required = true, example = "NotPassword1234")
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ApiModelProperty(name = "Email", value = "The email of the user", example = "JohnnyGuitar@Email.com")
    @Column(nullable = false,
            unique = true)
    private String email;

    @ApiModelProperty(name = "Company Name", value = "The name of the company at which the user is employed", example = "Air Guitars 'R' Us")
    @Column(nullable = false)
    private String companyname;

    @ApiModelProperty(name = "Company Role", value = "The role the user plays in the company", example = "Air Guitar Instructor")
    @Column(nullable = false)
    private String role;

    @ApiModelProperty(name = "User Events", value = "The events that the user is associated with")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    List<UserEvents> userEvents = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<UserRoles> userRoles = new ArrayList<>();

    @ApiModelProperty(name = "Image", value = "The Profile Image for the User")
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

    public User(String username, String password, List<UserRoles> userRoles)
    {
        setUsername(username);
        setPassword(password);
        for (UserRoles ur : userRoles)
        {
            ur.setUser(this);
        }
        this.userRoles = userRoles;
    }

    public List<UserRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public void setPasswordNoEncrypt(String password)
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

    public List<UserEvents> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(List<UserEvents> userEvents) {
        this.userEvents = userEvents;
    }

    public List<SimpleGrantedAuthority> getAuthority()
    {
        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

        for (UserRoles r : this.userRoles)
        {
            String myRole = "ROLE_" + r.getRole().getName().toUpperCase();
            rtnList.add(new SimpleGrantedAuthority(myRole));
        }

        return rtnList;
    }
}