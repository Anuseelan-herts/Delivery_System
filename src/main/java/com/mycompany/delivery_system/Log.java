/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.delivery_system;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Asus
 */
public class Log {
   private static Log instance; // Singleton instance
    private StringBuilder logBuffer;

    // Private constructor to prevent instantiation
    private Log() {
        logBuffer = new StringBuilder();
    }

    // Static method to get the single instance of the Log class
    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    // Method to add an event to the log
    public void logEvent(String event) {
        logBuffer.append(event).append("\n");
    }

    // Method to retrieve the log as a string
    public String getLog() {
        return logBuffer.toString();
    }

    // Method to write the log to a file
    public void writeLogToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(logBuffer.toString());
        } catch (IOException e) {
            System.err.println("Error writing log to file: " + e.getMessage());
        }
    }
}
