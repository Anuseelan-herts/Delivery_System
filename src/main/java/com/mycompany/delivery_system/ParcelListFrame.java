/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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


public class ParcelListFrame extends JFrame
// XXX: 1st Option: JFrame as listener
// implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private final ParcelManager parcelManager = ParcelManager.getInstance();
	private final JTable tblParcel = new JTable();
	private final JButton btnAdd = new JButton("Add Parcel"),
			btnMark = new JButton("Mark as Collected"),
			btnSave = new JButton("Save Report");
         List<Parcel> parcels;

	public ParcelListFrame() throws Exception
	{
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
    // This opens the AddParcelDialog to let the user input parcel details.
    AddParcelDialog dialog = new AddParcelDialog(this);
   
        display();
    
}


	private void mark()
	{
		int row = tblParcel.getSelectedRow();

		if (row != -1)
		{
			String parcelID = (String) tblParcel.getModel().getValueAt(row, 0);

			if (parcelID != null)
			{
				Parcel parcel = parcelManager.getParcel(parcelID);

				if (parcel != null && JOptionPane.showConfirmDialog(this,
						"Are you sure want to mark this parcel as collected?",
						getTitle(),
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				{
					if (parcelManager.deleteParcel(parcel))
					{
						JOptionPane.showMessageDialog(this,
								"Parcel " + parcel + " successfully collected.",
								getTitle(), JOptionPane.INFORMATION_MESSAGE);
						display();
					}
				}
			}
			else
				JOptionPane.showMessageDialog(this,
						"Unable to read the parcel ID.", getTitle(),
						JOptionPane.WARNING_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(this,
					"Please select a parcel to mark as collected.", getTitle(),
					JOptionPane.WARNING_MESSAGE);
	}

	private void save()
	{
		if (parcelManager.saveParcels())
		{
			btnSave.setEnabled(false);
			JOptionPane.showMessageDialog(this,
					"Successfully saved the operations.", getTitle(),
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void display() {
    // Read parcels from CSV
    parcels = readParcelsFromCSV("C:/Users/Asus/Documents/NetBeansProjects/Delivery_System/Delivery_System/src/main/java/com/mycompany/delivery_system/Parcels.csv");

    // Enable or disable save button based on whether any parcel is modified
    btnSave.setEnabled(parcelManager.isModified());

    // Update the table model with data from the CSV
    tblParcel.setModel(new DefaultTableModel(
        parcels.stream().map(parcel -> new String[] {
            parcel.getParcelID(),
            parcel.getDaysInDepot() + " day(s)",
            parcel.getWeight() + " kg(s)",
            parcel.getWidth() + " cm(s) x " + parcel.getHeight() 
            + " cm(s) x " + parcel.getLength() + " cm(s)"
        }).toArray(String[][]::new),
        new String[] {"Parcel ID", "Days in Depot", "Weight", "Dimension"}
    ));
}

private List<Parcel> readParcelsFromCSV(String filePath) {
    List<Parcel> parcels = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(","); // Assuming CSV is comma-separated
            if (values.length == 6) {
                // Assuming CSV fields are: ParcelID, DaysInDepot, Weight, Width, Height, Length
                String parcelID = values[0];
                int daysInDepot = Integer.parseInt(values[1]);
                double weight = Double.parseDouble(values[2]);
                double width = Double.parseDouble(values[3]);
                double height = Double.parseDouble(values[4]);
                double length = Double.parseDouble(values[5]);

                // Create a Parcel object and add to the list
                Parcel parcel = new Parcel(parcelID, daysInDepot, weight, width, height, length);
                parcels.add(parcel);
            }
        }
    } 
    catch (IOException e) {
        e.printStackTrace();
    }

    return parcels;
}

	public static void main(String[] args) throws Exception
	{
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		new ParcelListFrame();
	}
}