package com.cep.corporateeventplanner.model;

public class Purchase {
    private String description;
    private String vendorname;
    private String pointofcontact;
    private String email;
    private String price;
    private long qty;

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
