package com.mycompany.delivery_system;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;

public class ParcelListFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private final ParcelManager parcelManager = ParcelManager.getInstance();
    private final JTable tblParcel = new JTable();
    private final JButton btnAdd = new JButton("Add Parcel"),
            btnMark = new JButton("Mark as Collected"),
            btnSave = new JButton("Save Report");
    private List<Parcel> parcels;

    public ParcelListFrame() throws Exception {
        super("Parcel List - Depot Management System");

        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnAdd.addActionListener(e -> add());
        btnMark.addActionListener(e -> mark());
        btnSave.addActionListener(e -> save());

        tblParcel.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        display();

        pnlSouth.add(btnAdd);
        pnlSouth.add(btnMark);
        pnlSouth.add(btnSave);

        this.add(new JScrollPane(tblParcel), BorderLayout.CENTER);
        this.add(pnlSouth, BorderLayout.SOUTH);

        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void add() {
        AddParcelDialog dialog = new AddParcelDialog(this);
        display();
    }

    private void mark() {
        int row = tblParcel.getSelectedRow();

        if (row != -1) {
            String parcelID = (String) tblParcel.getModel().getValueAt(row, 0);

            if (parcelID != null) {
                Parcel parcel = parcelManager.getParcel(parcelID);

                if (parcel != null && JOptionPane.showConfirmDialog(this,
                        "Are you sure want to mark this parcel as collected?",
                        getTitle(),
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (parcelManager.deleteParcel(parcel)) {
                        JOptionPane.showMessageDialog(this,
                                "Parcel " + parcel + " successfully collected.",
                                getTitle(), JOptionPane.INFORMATION_MESSAGE);
                        display();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Unable to read the parcel ID.", getTitle(),
                        JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please select a parcel to mark as collected.", getTitle(),
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void save() {
        if (parcelManager.saveParcels()) {
            btnSave.setEnabled(false);
            JOptionPane.showMessageDialog(this,
                    "Successfully saved the operations.", getTitle(),
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void display() {
        parcels = readParcelsFromCSV("C:/Users/Asus/Documents/NetBeansProjects/Delivery_System/Delivery_System/src/main/java/com/mycompany/delivery_system/Parcels.csv");

        btnSave.setEnabled(parcelManager.isModified());

        tblParcel.setModel(new DefaultTableModel(
                parcels.stream().map(parcel -> new String[] {
                        parcel.getParcelID(),
                        parcel.getDaysInDepot() + " day(s)",
                        parcel.getWeight() + " kg(s)",
                        parcel.getWidth() + " cm(s) x " + parcel.getHeight()
                                + " cm(s) x " + parcel.getLength() + " cm(s)",
                        parcel.getCustomerID(),
                        String.format("%.2f", parcel.getDeliveryFee())
                }).toArray(String[][]::new),
                new String[] {"Parcel ID", "Days in Depot", "Weight", "Dimension", "Customer ID", "Parcel Fee"}
        ));
    }
private List<Parcel> readParcelsFromCSV(String filePath) {
    List<Parcel> parcels = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        boolean firstLine = true;  // Flag to skip the header line

        while ((line = br.readLine()) != null) {
            // Skip the first line (header)
            if (firstLine) {
                firstLine = false;
                continue;
            }

            String[] values = line.split(",");
            if (values.length == 8) { // Ensure there are 8 columns
                String parcelID = values[0];
                String customerID = values[1];  // customerID is a string, not a number
                int daysInDepot = Integer.parseInt(values[2]);  // daysInDepot is numeric
                double weight = Double.parseDouble(values[3]);
                double width = Double.parseDouble(values[4]);
                double height = Double.parseDouble(values[5]);
                double length = Double.parseDouble(values[6]);
                double parcelFee = Double.parseDouble(values[7]);

                Parcel parcel = new Parcel(parcelID, customerID, daysInDepot, weight, width, height, length, parcelFee);
                parcels.add(parcel);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return parcels;
}



    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        new ParcelListFrame();
    }
}
