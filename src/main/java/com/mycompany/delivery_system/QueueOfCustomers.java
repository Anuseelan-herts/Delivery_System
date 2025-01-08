/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.delivery_system;

import java.util.LinkedList;
import java.util.Queue;

public class QueueOfCustomers {
    private Queue<Customer> customerQueue;

    public QueueOfCustomers() {
        customerQueue = new LinkedList<>();
    }

    // Add a customer to the queue
    public void addCustomer(Customer customer) {
        customerQueue.add(customer);
    }

    // Process and return the next customer in the queue
    public Customer processNextCustomer() {
        return customerQueue.poll();  // poll removes and returns the next customer (FIFO)
    }

    // Check if there are customers in the queue
    public boolean hasNextCustomer() {
        return !customerQueue.isEmpty();  // returns true if the queue is not empty
    }
}
