package com.example.demo4;


public class RentalHistory {
     String customer;
     int date;
     String duration;
     String vehicle;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public RentalHistory(String customer, int date, String duration, String vehicle) {
        this.customer = customer;
        this.date = date;
        this.duration = duration;
        this.vehicle = vehicle;


    }
}
