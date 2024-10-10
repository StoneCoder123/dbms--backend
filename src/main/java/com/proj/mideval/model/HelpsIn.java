package com.proj.mideval.model;

public class HelpsIn {
    private int surgeryID;
    private int staffID;
    private int rating;

    // Constructors
    public HelpsIn() {}

    public HelpsIn(int surgeryID, int staffID, int rating) {
        this.surgeryID = surgeryID;
        this.staffID = staffID;
        this.rating = rating;
    }

    // Getters and Setters
    public int getSurgeryID() { return surgeryID; }
    public void setSurgeryID(int surgeryID) { this.surgeryID = surgeryID; }

    public int getStaffID() { return staffID; }
    public void setStaffID(int staffID) { this.staffID = staffID; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
}
