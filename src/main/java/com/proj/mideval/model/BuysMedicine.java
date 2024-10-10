package com.proj.mideval.model;


import java.sql.Date;

public class BuysMedicine {
    private int patientID;
    private int chemistID;
    private Date date;

    // Constructors
    public BuysMedicine() {}

    public BuysMedicine(int patientID, int chemistID, Date date) {
        this.patientID = patientID;
        this.chemistID = chemistID;
        this.date = date;
    }

    // Getters and Setters
    public int getPatientID() { return patientID; }
    public void setPatientID(int patientID) { this.patientID = patientID; }

    public int getChemistID() { return chemistID; }
    public void setChemistID(int chemistID) { this.chemistID = chemistID; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}

