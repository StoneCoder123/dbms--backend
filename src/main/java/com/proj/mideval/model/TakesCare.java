package com.proj.mideval.model;

public class TakesCare {
    private int patientID;
    private int staffID;
    private int rating;
    private String feedback;

    // Constructors
    public TakesCare() {}

    public TakesCare(int patientID, int staffID, int rating, String feedback) {
        this.patientID = patientID;
        this.staffID = staffID;
        this.rating = rating;
        this.feedback = feedback;
    }

    // Getters and Setters
    public int getPatientID() { return patientID; }
    public void setPatientID(int patientID) { this.patientID = patientID; }

    public int getStaffID() { return staffID; }
    public void setStaffID(int staffID) { this.staffID = staffID; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
}
