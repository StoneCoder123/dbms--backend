
package com.proj.mideval.model;

import java.util.Date;

public class Surgery {
    private int surgeryID;
    private int patientID;
    private int doctorID;
    private int billID;
    private String type;
    private int criticalLevel;
    private Date time;  // Changed from LocalDateTime to Date

    // Constructors
    public Surgery() {}

    public Surgery(int surgeryID, int patientID, int doctorID, int billID, String type, int criticalLevel, Date time) {
        this.surgeryID = surgeryID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.billID = billID;
        this.type = type;
        this.criticalLevel = criticalLevel;
        this.time = time;
    }

    // Getters and Setters
    public int getSurgeryID() { return surgeryID; }
    public void setSurgeryID(int surgeryID) { this.surgeryID = surgeryID; }

    public int getPatientID() { return patientID; }
    public void setPatientID(int patientID) { this.patientID = patientID; }

    public int getDoctorID() { return doctorID; }
    public void setDoctorID(int doctorID) { this.doctorID = doctorID; }

    public int getBillID() { return billID; }
    public void setBillID(int billID) { this.billID = billID; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getCriticalLevel() { return criticalLevel; }
    public void setCriticalLevel(int criticalLevel) { this.criticalLevel = criticalLevel; }

    public Date getTime() { return time; }  // Updated return type to Date
    public void setTime(Date time) { this.time = time; }  // Updated parameter type to Date
}

