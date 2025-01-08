package com.mycompany.delivery_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainMenuFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public MainMenuFrame() {
        super("Main Menu - Depot Management System");

        // Panel for menu options
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        // Buttons for menu options
        JButton btnViewParcel = new JButton("View Parcel List");
        JButton btnCustomerDetails = new JButton("View Customer Details");
        JButton btnFindParcel = new JButton("Find Parcel by ID");

        // Add buttons to the panel
        panel.add(btnViewParcel);
        panel.add(btnCustomerDetails);
        panel.add(btnFindParcel);

        // Add panel to the frame
        this.add(panel, BorderLayout.CENTER);

        // Button listeners for each option
        btnViewParcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ParcelListFrame(); // Open ParcelListFrame
                } catch (Exception ex) {
                    Logger.getLogger(MainMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                dispose(); // Close the main menu screen
            }
        });

        btnCustomerDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 try {
                    new CustomerDetailsFrame(); // Open ParcelListFrame
                } catch (Exception ex) {
                    Logger.getLogger(MainMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                dispose();
            }
        });

        btnFindParcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open FindParcelFrame (you can create this frame later)
                JOptionPane.showMessageDialog(MainMenuFrame.this, "Find Parcel by ID option clicked.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        this.setSize(300, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Center on screen
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenuFrame(); // Show main menu after login
            }
        });
    }
}
