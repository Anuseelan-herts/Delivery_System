/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.delivery_system;

import java.io.*;
import java.util.*;

public class Customer {
    private String customerId;
    private String name;
    private String address;
    private String phoneNumber;

    // Constructor
    public Customer(String customerId, String name, String address, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    Customer() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Convert customer data to CSV format (for saving to file)
    public String toCSV() {
        return customerId + "," + name + "," + address + "," + phoneNumber;
    }

    // Static method to create a customer from a CSV line (for reading from file)
    public static Customer fromCSV(String csvLine) {
        String[] values = csvLine.split(",");
        return new Customer(values[0], values[1], values[2], values[3]);
    }

    // File path where the customer data is saved
    private static final String FILE_PATH = "customer_data.txt"; // Text file instead of CSV

    // Method to save customer data to a file using BufferedWriter
    public static void saveCustomerToFile(Customer customer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(customer.toCSV());
            writer.newLine(); // Add a newline after each customer record
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read all customers from the file using BufferedReader
    public static List<Customer> readCustomersFromFile() {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                customers.add(fromCSV(line)); // Convert each CSV line to a Customer object
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

    // Method to search for a customer by customerId using BufferedReader
    public static Customer searchCustomerById(String customerId) {
        List<Customer> customers = readCustomersFromFile();
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null; // Return null if customer not found
    }

    // Main method for testing
    public static void main(String[] args) {
        // Creating a new customer
        Customer newCustomer = new Customer("C001", "John Doe", "1234 Elm Street", "555-1234");

        // Saving the customer to the file
        saveCustomerToFile(newCustomer);

        // Reading and printing all customers
        List<Customer> customers = readCustomersFromFile();
        System.out.println("All Customers:");
        for (Customer customer : customers) {
            System.out.println("ID: " + customer.getCustomerId() + ", Name: " + customer.getName());
        }

        // Searching for a customer by ID
        Customer foundCustomer = searchCustomerById("C001");
        if (foundCustomer != null) {
            System.out.println("Customer Found: " + foundCustomer.getName() + ", Address: " + foundCustomer.getAddress());
        } else {
            System.out.println("Customer not found!");
        }
    }
}