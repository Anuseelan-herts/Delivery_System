package com.mycompany.delivery_system;

import java.io.*;
import java.util.*;

public class Parcel {

    private String parcelId, customerid;
    private double weight;
    private double height, width, length;

    // Updated constructor with customerid
    public Parcel(String parcelId, double weight, double height, double width, double length, String cusid) {
        this.parcelId = parcelId;
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;
        this.customerid = cusid;
    }

    // Default constructor (if needed)
    public Parcel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated method
    }

    // Calculate the volume of the parcel
    public double calculateVolume() {
        return height * width * length;
    }

    public String getParcelId() {
        return parcelId;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public String getCustomerId() {
        return customerid;
    }

    // Convert parcel data to CSV format (for saving to file)
    public String toCSV() {
        return parcelId + "," + weight + "," + height + "," + width + "," + length + "," + customerid;
    }

    // Static method to create a Parcel from a CSV line (for reading from file)
    public static Parcel fromCSV(String csvLine) {
        String[] values = csvLine.split(",");
        return new Parcel(values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]),
                Double.parseDouble(values[3]), Double.parseDouble(values[4]), values[5]);
    }

    // File path where the parcel data is saved
    private static final String FILE_PATH = "parcel_data.txt"; // Text file instead of CSV

    // Method to save parcel data to a file using BufferedWriter
    public static void saveParcelToFile(Parcel parcel) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(parcel.toCSV());
            writer.newLine(); // Add a newline after each parcel record
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read all parcels from the file using BufferedReader
    public static List<Parcel> readParcelsFromFile() {
        List<Parcel> parcels = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parcels.add(fromCSV(line)); // Convert each CSV line to a Parcel object
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parcels;
    }

    // Method to search for a parcel by parcelId using BufferedReader
    public static Parcel searchParcelById(String parcelId) {
        List<Parcel> parcels = readParcelsFromFile();
        for (Parcel parcel : parcels) {
            if (parcel.getParcelId().equals(parcelId)) {
                return parcel;
            }
        }
        return null; // Return null if parcel not found
    }

    // Method to search for parcels by customerId
    public static List<Parcel> searchParcelsByCustomerId(String customerId) {
        List<Parcel> parcels = readParcelsFromFile();
        List<Parcel> customerParcels = new ArrayList<>();
        for (Parcel parcel : parcels) {
            if (parcel.getCustomerId().equals(customerId)) {
                customerParcels.add(parcel);
            }
        }
        return customerParcels; // Return a list of parcels belonging to the customer
    }

    // Main method for testing
    public static void main(String[] args) {
        // Creating new parcels
        Parcel newParcel1 = new Parcel("P001", 10.5, 2.0, 3.0, 4.0, "C001");
        Parcel newParcel2 = new Parcel("P002", 5.0, 1.5, 2.5, 3.5, "C002");

        // Saving parcels to the file
        saveParcelToFile(newParcel1);
        saveParcelToFile(newParcel2);

        // Reading and printing all parcels
        List<Parcel> parcels = readParcelsFromFile();
        System.out.println("All Parcels:");
        for (Parcel parcel : parcels) {
            System.out.println("Parcel ID: " + parcel.getParcelId() + ", Customer ID: " + parcel.getCustomerId() + ", Weight: " + parcel.getWeight());
        }

        // Searching for a parcel by ID
        Parcel foundParcel = searchParcelById("P001");
        if (foundParcel != null) {
            System.out.println("Parcel Found: " + foundParcel.getParcelId() + ", Customer ID: " + foundParcel.getCustomerId() + ", Volume: " + foundParcel.calculateVolume());
        } else {
            System.out.println("Parcel not found!");
        }

        // Searching for parcels by customer ID
        List<Parcel> customerParcels = searchParcelsByCustomerId("C001");
        System.out.println("Parcels for Customer ID C001:");
        for (Parcel parcel : customerParcels) {
            System.out.println("Parcel ID: " + parcel.getParcelId() + ", Weight: " + parcel.getWeight());
        }
    }
}
