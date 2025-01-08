/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.delivery_system;

public class Log {
    private StringBuffer logBuffer;

    public Log() {
        logBuffer = new StringBuffer();
    }

    // Add a log entry
    public void addLog(String logEntry) {
        logBuffer.append(logEntry).append("\n");
    }

    // Get all logs
    public String getLogs() {
        return logBuffer.toString();
    }
}
