/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.delivery_system;

/**
 *
 * @author Asus
 */
public class Parcel {
 
     private String parcelId;
    private double weight;
    private double height, width, length;

    public Parcel(String parcelId, double weight, double height, double width, double length) {
        this.parcelId = parcelId;
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public double calculateVolume() {
        return height * width * length;
    }

    public String getParcelId() {
        return parcelId;
    }
    
    public double getWeight() {
        return weight;
    }
    
    
}
