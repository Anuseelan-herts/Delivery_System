package com.mycompany.delivery_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDetailsFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTable tblCustomerDetails;

    public CustomerDetailsFrame() {
        super("Customer Details - Depot Management System");

        // Panel for customer details
        JPanel panel = new JPanel(new BorderLayout());
        tblCustomerDetails = new JTable();

        // Load customer data from CSV file
        List<String[]> customers = readCustomerDetailsFromCSV("C:/Users/Asus/Documents/NetBeansProjects/Delivery_System/Delivery_System/src/main/java/com/mycompany/delivery_system/Custs.csv");

        // Create a table model
        DefaultTableModel model = new DefaultTableModel(
                new Object[][] {},
                new String[] {"Customer ID", "Name", "Email", "Phone Number", "Address"}
        );

        // Fill the table with customer data
        for (String[] customer : customers) {
            model.addRow(new Object[]{
                    customer[0],  // Customer ID
                    customer[1],  // Name
                    customer[2],  // Email
                    customer[3],  // Phone Number
                    customer[4]   // Address
            });
        }

        tblCustomerDetails.setModel(model);

        JScrollPane scrollPane = new JScrollPane(tblCustomerDetails);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add the panel to the frame
        this.add(panel, BorderLayout.CENTER);

        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null); // Center on screen
        this.setVisible(true);
    }

    private List<String[]> readCustomerDetailsFromCSV(String filePath) {
        List<String[]> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip the header line if necessary
            reader.readLine();  // Uncomment this line if your CSV has a header row

            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                // Split the line by comma
                String[] customer = line.split(",");
                customers.add(customer);  // Add the customer data to the list
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading CSV file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return customers;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CustomerDetailsFrame();
            }
        });
    }
}
