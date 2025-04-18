package com.example.demo4;

public class Customer {
     String name;
     String licence;
     String contact;
     String rentalInfo;

    public Customer(String name, String licence, String contact, String rentalInfo) {
        this.name = name;
        this.licence = licence;
        this.contact = contact;
        this.rentalInfo = rentalInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRentalInfo() {
        return rentalInfo;
    }

    public void setRentalInfo(String rentalInfo) {
        this.rentalInfo = rentalInfo;
    }
}
