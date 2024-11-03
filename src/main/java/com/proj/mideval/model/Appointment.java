package com.proj.mideval.model;

import java.util.Date;

public class Appointment {
    private int appointmentID;
    private int patientID;
    private int doctorID;
    private Date time;
    private int status;
    private int cost;// 0 = requested, 1 = granted

    // Constructors
    public Appointment() {}

    public Appointment(int appointmentID, int patientID, int doctorID, Date time, int status, int cost) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.time = time;
        this.status = status;
        this.cost = cost;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}

