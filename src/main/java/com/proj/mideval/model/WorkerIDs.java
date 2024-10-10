package com.proj.mideval.model;

public class WorkerIDs {
    private int doctorID;
    private int staffID;
    private int chemistID;
    private String workerID;

    // Constructors
    public WorkerIDs() {}

    public WorkerIDs(int doctorID, int staffID, int chemistID, String workerID) {
        this.doctorID = doctorID;
        this.staffID = staffID;
        this.chemistID = chemistID;
        this.workerID = workerID;
    }

    // Getters and Setters
    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public int getChemistID() {
        return chemistID;
    }

    public void setChemistID(int chemistID) {
        this.chemistID = chemistID;
    }

    public String getWorkerID() {
        return workerID;
    }

    public void setWorkerID(String workerID) {
        this.workerID = workerID;
    }
}
