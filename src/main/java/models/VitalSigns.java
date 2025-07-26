package models;

import java.util.Date;

public class VitalSigns {
    private String patientId;
    private double temperature;
    private int heartRate;
    private Date timestamp;
    
    public VitalSigns(String patientId, double temp, int hr) {
        this.patientId = patientId;
        this.temperature = temp;
        this.heartRate = hr;
        this.timestamp = new Date();
    }
    
    // Getters
    public String getPatientId() { return patientId; }
    public double getTemperature() { return temperature; }
    public int getHeartRate() { return heartRate; }
    public Date getTimestamp() { return timestamp; }
    
    @Override
    public String toString() {
        return String.format("%s - Temp: %.1f°C, HR: %d", 
            patientId, temperature, heartRate);
    }
}