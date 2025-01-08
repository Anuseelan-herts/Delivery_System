/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.delivery_system;

import java.util.HashMap;
import java.util.Map;

public class ParcelMap {
    private Map<String, Parcel> parcelMap;

    public ParcelMap() {
        parcelMap = new HashMap<>();
    }

    // Add a parcel to the map
    public void addParcel(String customerId, Parcel parcel) {
        parcelMap.put(customerId, parcel);
    }

    // Retrieve a parcel based on customer ID
    public Parcel getParcel(String customerId) {
        return parcelMap.get(customerId);
    }

    // Check if a parcel exists for a customer
    public boolean hasParcel(String customerId) {
        return parcelMap.containsKey(customerId);
    }
}
