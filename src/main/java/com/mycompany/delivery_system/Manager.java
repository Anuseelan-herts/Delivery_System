/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.delivery_system;

/**
 *
 * @author Asus
 */
public class Manager {
   public static void main(String[] args) {
        QueueOfCustomers queueOfCustomers = new QueueOfCustomers();
        ParcelMap parcelMap = new ParcelMap();

        // Example of loading data (replace with file reading logic)
        queueOfCustomers.addCustomer(new Customer("C001", "John Doe"));
        parcelMap.addParcel(new Parcel("P001", 5.0, 10.0, 10.0, 10.0));

        // Console-based interaction for testing
        Worker worker = new Worker();
        Customer customer = queueOfCustomers.removeCustomer();
        Parcel parcel = parcelMap.getParcel("P001");

        if (customer != null && parcel != null) {
            double fee = worker.calculateFee(parcel);
            worker.processCustomer(customer, parcel);
            System.out.println("Fee: " + fee);
        }

        // Print log to console
        System.out.println(Log.getInstance().getLog());
    }
}
