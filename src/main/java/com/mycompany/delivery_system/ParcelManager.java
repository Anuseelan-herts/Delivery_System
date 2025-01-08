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

    public boolean saveParcels() {
        // Implement saving logic, e.g., writing to a file
        modified = false;
        return true; // return true if successful
    }

    public boolean isModified() {
        return modified;
    }
    

    public List<Parcel> getParcels() {
        return parcels;
    }

    public Parcel getParcel(String parcelID) {
        for (Parcel parcel : parcels) {
            if (parcel.getParcelID().equals(parcelID)) {
                return parcel;
            }
        }
        return null;
    }

    public boolean deleteParcel(Parcel parcel) {
        if (parcels.remove(parcel)) {
            modified = true;
            return true;
        }
        return false;
    }

}
