package com.proj.mideval.model;


public class PatientPhone {
    private String phone;
    private int patientID;

    // Constructors
    public PatientPhone() {}

    public PatientPhone(String phone, int patientID) {
        this.phone = phone;
        this.patientID = patientID;
    }

    // Getters and Setters
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public int getPatientID() { return patientID; }
    public void setPatientID(int patientID) { this.patientID = patientID; }
}
