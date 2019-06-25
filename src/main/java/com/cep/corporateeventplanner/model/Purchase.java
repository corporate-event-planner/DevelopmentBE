package com.cep.corporateeventplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Generated;

import javax.persistence.*;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long purchaseid;


    private String description;
    private String vendorname;
    private String pointofcontact;
    private String email;
    private String price;
    private long qty;

    @ManyToOne
    @JoinColumn(name = "purchase")
    @JsonIgnoreProperties(value = "purchase")
    private Task task;

    public Purchase() {
    }

    public Purchase(String description, String vendorname, String pointofcontact, String email, String price, long qty) {
        this.description = description;
        this.vendorname = vendorname;
        this.pointofcontact = pointofcontact;
        this.email = email;
        this.price = price;
        this.qty = qty;
    }

    public Purchase(String description, String vendorname, String pointofcontact, String email, String price) {
        this.description = description;
        this.vendorname = vendorname;
        this.pointofcontact = pointofcontact;
        this.email = email;
        this.price = price;
    }

    public Purchase(String description, String vendorname, String pointofcontact, String email, String price, long qty, Task task) {
        this.description = description;
        this.vendorname = vendorname;
        this.pointofcontact = pointofcontact;
        this.email = email;
        this.price = price;
        this.qty = qty;
        this.task = task;
    }

    public long getPurchaseid() {
        return purchaseid;
    }

    public void setPurchaseid(long purchaseid) {
        this.purchaseid = purchaseid;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public String getPointofcontact() {
        return pointofcontact;
    }

    public void setPointofcontact(String pointofcontact) {
        this.pointofcontact = pointofcontact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "description='" + description + '\'' +
                ", vendorname='" + vendorname + '\'' +
                ", pointofcontact='" + pointofcontact + '\'' +
                ", email='" + email + '\'' +
                ", price='" + price + '\'' +
                ", qty=" + qty +
                '}';
    }
}