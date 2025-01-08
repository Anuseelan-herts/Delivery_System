/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.delivery_system;


import java.util.ArrayList;
import java.util.List;

public class ParcelManager {
    private static ParcelManager instance;
    private List<Parcel> parcels;
    private boolean modified;

    private ParcelManager() {
        parcels = new ArrayList<>();
        modified = false;
    }

    public static ParcelManager getInstance() {
        if (instance == null) {
            instance = new ParcelManager();
        }
        return instance;
    }

    public boolean addParcel(Parcel parcel) {
        modified = true;
        return parcels.add(parcel);
    }

    public boolean deleteParcel(Parcel parcel) {
        modified = true;
        return parcels.remove(parcel);
    }

    public Parcel getParcel(String parcelID) {
        return parcels.stream()
                      .filter(parcel -> parcel.getParcelID().equals(parcelID))
                      .findFirst()
                      .orElse(null);
    }

    public List<Parcel> getParcels() {
        return parcels;
    }

    public boolean isModified() {
        return modified;
    }

    public boolean saveParcels() {
        // Simulate saving to a file or database
        modified = false;
        return true;
    }
}
