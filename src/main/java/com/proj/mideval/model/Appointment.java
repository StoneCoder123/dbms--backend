package com.proj.mideval.model;

import java.util.Date;

public class Appointment {
    private int appointmentID;
    private int patientID;
    private int doctorID;
    private Date time;
    private int status;
    private Integer billID; // Bill ID can be null, so we use Integer
    private String prescription; // Prescription as a string to hold up to 500 characters

    // Constructors
    public Appointment() {}

    public Appointment(int appointmentID, int patientID, int doctorID, Date time, int status, Integer billID, String prescription) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.time = time;
        this.status = status;
        this.billID = billID;
        this.prescription = prescription;
    }

    // Getters and Setters
    public int getAppointmentID() { return appointmentID; }
    public void setAppointmentID(int appointmentID) { this.appointmentID = appointmentID; }

    public int getPatientID() { return patientID; }
    public void setPatientID(int patientID) { this.patientID = patientID; }

    public int getDoctorID() { return doctorID; }
    public void setDoctorID(int doctorID) { this.doctorID = doctorID; }

    public Date getTime() { return time; }
    public void setTime(Date time) { this.time = time; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public Integer getBillID() { return billID; }
    public void setBillID(Integer billID) { this.billID = billID; }

    public String getPrescription() { return prescription; }
    public void setPrescription(String prescription) { this.prescription = prescription; }
}
