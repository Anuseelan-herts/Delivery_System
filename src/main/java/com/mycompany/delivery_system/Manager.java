/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.delivery_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manager extends JFrame {
    
    private QueueOfCustomers queueOfCustomers;
    private ParcelMap parcelMap;
    private Log log;
    private JTextArea logTextArea;
    private JButton processButton;
    private JLabel customerLabel;
    private JLabel parcelLabel;
ParcelManager parcelManager;
    public Manager() {
        setTitle("Parcel Processing System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize objects
        log = new Log();
        queueOfCustomers = new QueueOfCustomers();
        parcelMap = new ParcelMap();

        // Read customer and parcel data from CSV files
        FileReaderUtil.readCustomers("customer.csv", queueOfCustomers);
        FileReaderUtil.readParcels("parcel.csv", parcelMap,parcelManager);

        // Set up the layout
        setLayout(new BorderLayout());

        // Customer and parcel info display
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayout(3, 1));
        customerLabel = new JLabel("Customer: " + queueOfCustomers.processNextCustomer().getName());
        parcelLabel = new JLabel("Parcel: " + (parcelMap.getParcel("X919") != null ? parcelMap.getParcel("X919") : "No parcel"));
        displayPanel.add(customerLabel);
        displayPanel.add(parcelLabel);
        add(displayPanel, BorderLayout.NORTH);

        // Log area
        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logTextArea);
        add(scrollPane, BorderLayout.CENTER);

        // Process button
        processButton = new JButton("Process Next Customer");
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Worker worker = new Worker(log);
                worker.processCustomer(queueOfCustomers, parcelMap);
                logTextArea.setText(log.getLogs());
                updateDisplay();
            }
        });
        add(processButton, BorderLayout.SOUTH);
    }

    private void updateDisplay() {
        if (queueOfCustomers.hasNextCustomer()) {
            Customer nextCustomer = queueOfCustomers.processNextCustomer();
            customerLabel.setText("Customer: " + nextCustomer.getName());
        } else {
            customerLabel.setText("No more customers in the queue");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Manager manager = new Manager();
            manager.setVisible(true);
        });
    }
}

