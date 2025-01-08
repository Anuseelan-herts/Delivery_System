package com.mycompany.delivery_system;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class FileReaderUtil {

    public static void readCustomers(String customerFilePath, QueueOfCustomers queue) {
        try (BufferedReader reader = new BufferedReader(new FileReader(customerFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String name = parts[0].trim();
                    String id = parts[1].trim();
                    queue.addCustomer(new Customer(name, id));
                } else {
                    System.err.println("Invalid customer data: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading customer file: " + e.getMessage());
        }
    }


public static void readParcels(String parcelFilePath, ParcelMap parcelMap, ParcelManager parcelManager) {
    try (BufferedReader reader = new BufferedReader(new FileReader(parcelFilePath))) {
        String line;

        // Skip the header row
        line = reader.readLine(); // Read and discard the first line (header)

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 8) { // Ensure all required fields are present
                try {
                    String parcelID = parts[0].trim();
                    String customerID = parts[1].trim();
                    int daysInDepot = Integer.parseInt(parts[2].trim());
                    double weight = Double.parseDouble(parts[3].trim());
                    double width = Double.parseDouble(parts[4].trim());
                    double height = Double.parseDouble(parts[5].trim());
                    double length = Double.parseDouble(parts[6].trim());
                    double deliveryFee = Double.parseDouble(parts[7].trim());

                    Parcel parcel = new Parcel(parcelID, customerID, daysInDepot, weight, width, height, length, deliveryFee);
                    parcelMap.addParcel(parcelID, parcel);  // Adding to the ParcelMap
                    parcelManager.addParcel(parcel);       // Adding to the existing ParcelManager
                } catch (NumberFormatException e) {
                    System.err.println("Invalid numeric value in parcel data: " + line);
                }
            } else {
                System.err.println("Invalid parcel data: " + line);
            }
        }
    } catch (IOException e) {
        System.err.println("Error reading parcel file: " + e.getMessage());
    }
}


}
