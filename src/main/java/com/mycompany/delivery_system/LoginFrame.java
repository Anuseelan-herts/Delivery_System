package com.mycompany.delivery_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    // Login details (username and password)
    private final String correctUsername = "anu"; 
    private final String correctPassword = "1998"; 

    private final JTextField txtUsername = new JTextField();
    private final JPasswordField txtPassword = new JPasswordField();
    private final JButton btnLogin = new JButton("Login");

    public LoginFrame() {
        super("Login - Depot Management System");

        // Panel for login form components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("Username:"));
        panel.add(txtUsername);
        panel.add(new JLabel("Password:"));
        panel.add(txtPassword);
        panel.add(new JLabel());
        panel.add(btnLogin);

        // Add panel to the frame
        this.add(panel, BorderLayout.CENTER);

        // Login button action listener
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                if (username.equals(correctUsername) && password.equals(correctPassword)) {
                    // Login successful, open the Main Menu
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new MainMenuFrame();
                    dispose();  // Close the login screen
                } else {
                    // Incorrect login, show error
                    JOptionPane.showMessageDialog(LoginFrame.this, "Incorrect username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
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
                new LoginFrame();  // Show login frame when the application starts
            }
        });
    }
}
