/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.delivery_system;

/**
 *
 * @author Asus
 */
public class Worker {
     public double calculateFee(Parcel parcel) {
        return parcel.getWeight() * 10; // Example fee calculation
    }

    public void processCustomer(Customer customer, Parcel parcel) {
        Log.getInstance().logEvent("Processed customer: " + customer.getName() + " with parcel ID: " + parcel.getParcelId());
    }
}
