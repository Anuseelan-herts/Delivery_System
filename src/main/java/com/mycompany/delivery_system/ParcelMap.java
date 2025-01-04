/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.delivery_system;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class ParcelMap {
     private Map<String, Parcel> parcels = new HashMap<>();

    public void addParcel(Parcel parcel) {
        parcels.put(parcel.getParcelId(), parcel);
    }

    public Parcel getParcel(String parcelId) {
        return parcels.get(parcelId);
    }
}
