package com.proj.mideval.model;


public class Bill {
    private int billID;
    private int patientID;
    private int totalCost;

    // Constructors
    public Bill() {}

    public Bill(int billID, int patientID, int totalCost) {
        this.billID = billID;
        this.patientID = patientID;
        this.totalCost = totalCost;
    }

    // Getters and Setters
    public int getBillID() { return billID; }
    public void setBillID(int billID) { this.billID = billID; }

    public int getPatientID() { return patientID; }
    public void setPatientID(int patientID) { this.patientID = patientID; }

    public int getTotalCost() { return totalCost; }
    public void setTotalCost(int totalCost) { this.totalCost = totalCost; }
}
