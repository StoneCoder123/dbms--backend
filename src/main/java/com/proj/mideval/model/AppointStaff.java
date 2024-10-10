package com.proj.mideval.model;

public class AppointStaff {
    private int adminID;
    private int staffID;
    private String remarks;
    private String strengths;
    private String weakness;

    // Constructors
    public AppointStaff() {}

    public AppointStaff(int adminID, int staffID, String remarks, String strengths, String weakness) {
        this.adminID = adminID;
        this.staffID = staffID;
        this.remarks = remarks;
        this.strengths = strengths;
        this.weakness = weakness;
    }

    // Getters and Setters
    public int getAdminID() { return adminID; }
    public void setAdminID(int adminID) { this.adminID = adminID; }

    public int getStaffID() { return staffID; }
    public void setStaffID(int staffID) { this.staffID = staffID; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getStrengths() { return strengths; }
    public void setStrengths(String strengths) { this.strengths = strengths; }

    public String getWeakness() { return weakness; }
    public void setWeakness(String weakness) { this.weakness = weakness; }
}
