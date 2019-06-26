package com.cep.corporateeventplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Generated;

import javax.persistence.*;

@ApiModel(value = "Purchase", description = "The purchase entity")
@Entity
public class Purchase {
    @ApiModelProperty(name = "Purchase ID", value = "The Primary Key ID for the purchase", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long purchaseid;

    @ApiModelProperty(name = "Purchase description", value = "The description of the purchase", example = "Hire a catering company")
    private String description;
    @ApiModelProperty(name = "Vendor Name", value = "The name of the potential vendor for the purchase", example = "A&G Catering")
    private String vendorname;
    @ApiModelProperty(name = "Point of Contact", value = "The contact for the vendor", example = "Jamie 602-341-1827")
    private String pointofcontact;
    @ApiModelProperty(name = "Contact Email", value = "The email of the vendor contact", example = "JamieIsThaBest@Email.com")
    private String email;
    @ApiModelProperty(name = "Price", value = "The estimated and/or maximum price of the purchase", example = "$1,500")
    private String price;
    @ApiModelProperty(name = "Quantity", value = "The quantity of the purchase", example = "100")
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