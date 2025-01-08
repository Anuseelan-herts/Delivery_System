/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.delivery_system;

public class Worker {
    private Log log;

    // Constructor to initialize the log instance
    public Worker(Log log) {
        this.log = log;
    }

    // Process the customer and calculate the fee for the parcel
    public void processCustomer(QueueOfCustomers queueOfCustomers, ParcelMap parcelMap) {
        // Check if there are customers in the queue
        if (queueOfCustomers.hasNextCustomer()) {
            // Process the next customer
            Customer customer = queueOfCustomers.processNextCustomer();

            // Get the parcel for this customer from the ParcelMap
            Parcel parcel = parcelMap.getParcel(customer.getId());

            // Calculate the fee (for simplicity, let's assume a fixed fee for now)
            double fee = calculateFee(parcel);

            // Log the processing of this customer and parcel
            log.addLog("Customer " + customer.getName() + " processed with parcel " + parcel + ". Fee: " + fee);

            // You can add more complex logic here as needed
        }
    }

    // Example of calculating a fee for the parcel
    private double calculateFee(Parcel parcel) {
        // Simple calculation: Sum of parcel attributes (just as an example)
        return parcel.getWeight() + parcel.getLength() + parcel.getWidth() + parcel.getHeight();
    }
}
