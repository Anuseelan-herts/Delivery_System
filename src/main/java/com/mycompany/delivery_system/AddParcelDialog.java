/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.delivery_system;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class AddParcelDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    private final ParcelManager parcelManager = ParcelManager.getInstance();
    private final JTextField txtParcelID = new JTextField();
    private final JSpinner spnDaysInDepot = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));
    private final JSpinner spnWeight = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D));
    private final JSpinner spnWidth = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D));
    private final JSpinner spnHeight = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D));
    private final JSpinner spnLength = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D));
    private final JButton btnAdd = new JButton("Add");
    private final JButton btnReset = new JButton("Reset");

    public AddParcelDialog(JFrame frame) {
        super(frame, "Add Parcel Dialog", true);

        // Panel for the form fields
        JPanel pnlCenter = new JPanel(new GridLayout(6, 2));
        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // Action listeners for buttons
        btnAdd.addActionListener(e -> add());
        btnReset.addActionListener(e -> reset());

        // Add form fields to the panel
        pnlCenter.add(new JLabel("Parcel ID: ", JLabel.RIGHT));
        pnlCenter.add(txtParcelID);
        pnlCenter.add(new JLabel("Days in Depot: ", JLabel.RIGHT));
        pnlCenter.add(spnDaysInDepot);
        pnlCenter.add(new JLabel("Weight (kg): ", JLabel.RIGHT));
        pnlCenter.add(spnWeight);
        pnlCenter.add(new JLabel("Width (cm): ", JLabel.RIGHT));
        pnlCenter.add(spnWidth);
        pnlCenter.add(new JLabel("Height (cm): ", JLabel.RIGHT));
        pnlCenter.add(spnHeight);
        pnlCenter.add(new JLabel("Length (cm): ", JLabel.RIGHT));
        pnlCenter.add(spnLength);

        // Add buttons to the south panel
        pnlSouth.add(btnAdd);
        pnlSouth.add(btnReset);

        // Add panels to the dialog
        this.add(pnlCenter, BorderLayout.CENTER);
        this.add(pnlSouth, BorderLayout.SOUTH);

        // Dialog properties
        this.setSize(320, 240);
        this.setResizable(false);
        this.setLocationRelativeTo(frame);
        this.setVisible(true);
    }

    // Method to add the parcel
    private void add() {
        Parcel parcel = new Parcel();
        parcel.setParcelID(txtParcelID.getText());
        parcel.setDaysInDepot((int) spnDaysInDepot.getValue());
        parcel.setWeight((double) spnWeight.getValue());
        parcel.setWidth((double) spnWidth.getValue());
        parcel.setHeight((double) spnHeight.getValue());
        parcel.setLength((double) spnLength.getValue());

     
            JOptionPane.showMessageDialog(this, "Parcel successfully added.",
                    getTitle(), JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
     
    }

    // Reset form fields to default values
    private void reset() {
        txtParcelID.setText("");
        spnDaysInDepot.setValue(1);
        spnWeight.setValue(1);
        spnWidth.setValue(1);
        spnHeight.setValue(1);
        spnLength.setValue(1);
    }
}
