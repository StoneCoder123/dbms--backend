package com.proj.mideval.model;

public class TreatmentProcedure {
    private int doctorID;
    private int patientID;
    private int critical;

    // Constructors
    public TreatmentProcedure() {
    }

    public TreatmentProcedure(int doctorID, int patientID, int critical) {
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.critical = critical;
    }

    // Getters and Setters

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }
}

