package com.proj.mideval.model;

public class Bill {
    private int billID;
    private int patientID;
    private int totalCost;
    private int status; // 0 by default
    private String type; // Type as a string to hold up to 100 characters

    // Constructors
    public Bill() {}

    public Bill(int billID, int patientID, int totalCost, int status, String type) {
        this.billID = billID;
        this.patientID = patientID;
        this.totalCost = totalCost;
        this.status = status;
        this.type = type;
    }

    // Getters and Setters
    public int getBillID() { return billID; }
    public void setBillID(int billID) { this.billID = billID; }

    public int getPatientID() { return patientID; }
    public void setPatientID(int patientID) { this.patientID = patientID; }

    public int getTotalCost() { return totalCost; }
    public void setTotalCost(int totalCost) { this.totalCost = totalCost; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
