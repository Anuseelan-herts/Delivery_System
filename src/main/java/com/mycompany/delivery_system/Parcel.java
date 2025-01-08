package com.mycompany.delivery_system;

public class Parcel {
    private String parcelID;
    private int daysInDepot;
    private double weight;
    private double width;
    private double height;
    private double length;

    // Constructor with parameters
    public Parcel(String parcelID, int daysInDepot, double weight, double width, double height, double length) {
        this.parcelID = parcelID;
        this.daysInDepot = daysInDepot;
        this.weight = weight;
        this.width = width;
        this.height = height;
        this.length = length;
    }

    // Default constructor
    public Parcel() {
        // You can set default values here if needed
        this.parcelID = "Unknown";
        this.daysInDepot = 0;
        this.weight = 0.0;
        this.width = 0.0;
        this.height = 0.0;
        this.length = 0.0;
    }

    // Getters and Setters
    public String getParcelID() {
        return parcelID;
    }

    public void setParcelID(String parcelID) {
        this.parcelID = parcelID;
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
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Parcel ID: " + parcelID + ", Dimensions: " + width + "x" +
               height + "x" + length + ", Weight: " + weight + "kg";
    }
}
