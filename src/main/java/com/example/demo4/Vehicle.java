package com.example.demo4;

public class Vehicle {
     String vehicleID;
     String brand;
     String category;
     double rentalPrice;
     String availability;

    public Vehicle(String vehicleID, String brand, String category, double rentalPrice, String availability) {
        this.vehicleID = vehicleID;
        this.brand = brand;
        this.category = category;
        this.rentalPrice = rentalPrice;
        this.availability = availability;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
