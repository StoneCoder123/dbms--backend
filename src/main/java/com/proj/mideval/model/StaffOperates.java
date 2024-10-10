package com.proj.mideval.model;

public class StaffOperates {
    private int machineID;
    private int staffID;

    // Constructors
    public StaffOperates() {}

    public StaffOperates(int machineID, int staffID) {
        this.machineID = machineID;
        this.staffID = staffID;
    }

    // Getters and Setters
    public int getMachineID() { return machineID; }
    public void setMachineID(int machineID) { this.machineID = machineID; }

    public int getStaffID() { return staffID; }
    public void setStaffID(int staffID) { this.staffID = staffID; }
}
