package com.mycompany.delivery_system;

public class Parcel {
    private String parcelID;
    private String customerID; // New field for Customer ID
    private int daysInDepot;
    private double weight;
    private double width;
    private double height;
    private double length;
    private double deliveryFee; // New field for delivery fee

    // Constructor with all parameters
    public Parcel(String parcelID, String customerID, int daysInDepot, double weight, double width, double height, double length, double deliveryFee) {
        this.parcelID = parcelID;
        this.customerID = customerID;
        this.daysInDepot = daysInDepot;
        this.weight = weight;
        this.width = width;
        this.height = height;
        this.length = length;
        this.deliveryFee = deliveryFee;
    }

    // Constructor without delivery fee (calculates it)
    public Parcel(String parcelID, String customerID, int daysInDepot, double weight, double width, double height, double length) {
        this.parcelID = parcelID;
        this.customerID = customerID;
        this.daysInDepot = daysInDepot;
        this.weight = weight;
        this.width = width;
        this.height = height;
        this.length = length;
        this.deliveryFee = calculateDeliveryFee(); 
    }

    // Default constructor
    public Parcel() {
        this.parcelID = "Unknown";
        this.customerID = "Unknown";
        this.daysInDepot = 0;
        this.weight = 0.0;
        this.width = 0.0;
        this.height = 0.0;
        this.length = 0.0;
        this.deliveryFee = 0.0;
    }

    // Getters and Setters
    public String getParcelID() {
        return parcelID;
    }

    public void setParcelID(String parcelID) {
        this.parcelID = parcelID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int getDaysInDepot() {
        return daysInDepot;
    }

    public void setDaysInDepot(int daysInDepot) {
        this.daysInDepot = daysInDepot;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        this.deliveryFee = calculateDeliveryFee(); 
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
        this.deliveryFee = calculateDeliveryFee(); 
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        this.deliveryFee = calculateDeliveryFee(); 
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
        this.deliveryFee = calculateDeliveryFee();
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    private double calculateDeliveryFee() {
        double volume = width * height * length;
        return (weight * 0.5) + (volume * 0.001); 
    }

    @Override
    public String toString() {
        return "Parcel ID: " + parcelID + 
               ", Customer ID: " + customerID + 
               ", Dimensions: " + width + "x" + height + "x" + length + 
               ", Weight: " + weight + "kg" + 
               ", Delivery Fee: $" + deliveryFee;
    }
}
