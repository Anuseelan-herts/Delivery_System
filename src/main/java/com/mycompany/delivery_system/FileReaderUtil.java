/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
                String name = parts[0].trim();
                String id = parts[1].trim();
                queue.addCustomer(new Customer(name, id));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readParcels(String parcelFilePath, ParcelMap parcelMap) {
        try (BufferedReader reader = new BufferedReader(new FileReader(parcelFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String customerId = parts[0].trim();
                int daysInDepot = Integer.parseInt(parts[1].trim());
                int weight = Integer.parseInt(parts[2].trim());
                int width = Integer.parseInt(parts[3].trim());
                int height = Integer.parseInt(parts[4].trim());
                int length = Integer.parseInt(parts[5].trim());

                Parcel parcel = new Parcel(customerId, daysInDepot, weight, width, height, length);
                parcelMap.addParcel(customerId, parcel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
