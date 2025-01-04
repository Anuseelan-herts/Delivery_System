/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



package com.mycompany.delivery_system;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Asus
 */
public class QueueOfCustomers {
    
    private Queue<Customer> customerQueue = new LinkedList<>();

    public void addCustomer(Customer customer) {
        customerQueue.add(customer);
    }

    public Customer removeCustomer() {
        return customerQueue.poll();
    }

    public Queue<Customer> getCustomerQueue() {
        return customerQueue;
    }
    
}
