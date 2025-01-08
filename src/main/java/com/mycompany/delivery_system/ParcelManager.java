package com.mycompany.delivery_system;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParcelManager {
    private static ParcelManager instance;
    private List<Parcel> parcels;
    private boolean modified;

    ParcelManager() {
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

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/Asus/Documents/NetBeansProjects/Delivery_System/Delivery_System/src/main/java/com/mycompany/delivery_system/Parcels.csv"))) {

            bw.write("parcelID,customerID,daysInDepot,weight,width,height,length,deliveryFee");
            bw.newLine();

            // Iterate through parcels and write the ones that are not the selected parcel
            for (Parcel p : parcels) {
                // If this parcel is not the selected one, write it back to the file
                if (!p.equals(parcel)) {
                    String parcelData = p.getParcelID() + "," 
                        + p.getCustomerID() + "," 
                        + p.getDaysInDepot() + ","
                        + p.getWeight() + ","
                        + p.getWidth() + ","
                        + p.getHeight() + ","
                        + p.getLength() + ","
                        + p.getDeliveryFee();

                    bw.write(parcelData);
                    bw.newLine();  // Add a new line after writing the parcel data
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;  // Return false if there was an error during the write
        }

   
        return true;
    }
    return false;
}


    // New method to add a parcel
  public boolean addParcel(Parcel parcel) {
    if (parcel != null) {
        parcels.add(parcel);
        modified = true;

        // Append the new parcel data to the parcel.csv file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/Asus/Documents/NetBeansProjects/Delivery_System/Delivery_System/src/main/java/com/mycompany/delivery_system/Parcels.csv", true))) {
            String parcelData = parcel.getParcelID() + "," 
                + parcel.getCustomerID() + "," 
                + parcel.getDaysInDepot() + ","
                + parcel.getWeight() + ","
                + parcel.getWidth() + ","
                + parcel.getHeight() + ","
                + parcel.getLength() + ","
                + parcel.getDeliveryFee();

            bw.write(parcelData);
            bw.newLine();  // Add a new line after writing the parcel data

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    return false;
}

    // Method to calculate delivery fee (if applicable)
    public double calculateDeliveryFee(Parcel parcel) {
        double baseFee = 10.0;
        double weightRate = 2.0; // Example rate per unit weight
        return baseFee + (parcel.getWeight() * weightRate);
    }
}
