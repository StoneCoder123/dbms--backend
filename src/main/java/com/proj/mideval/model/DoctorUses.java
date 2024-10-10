package com.proj.mideval.model;

public class DoctorUses {
    private int doctorID;
    private int machineID;
    private int accessLevel;
    private int costPerHour;

    // Constructors
    public DoctorUses() {}

    public DoctorUses(int doctorID, int machineID, int accessLevel, int costPerHour) {
        this.doctorID = doctorID;
        this.machineID = machineID;
        this.accessLevel = accessLevel;
        this.costPerHour = costPerHour;
    }

    // Getters and Setters
    public int getDoctorID() { return doctorID; }
    public void setDoctorID(int doctorID) { this.doctorID = doctorID; }

    public int getMachineID() { return machineID; }
    public void setMachineID(int machineID) { this.machineID = machineID; }

    public int getAccessLevel() { return accessLevel; }
    public void setAccessLevel(int accessLevel) { this.accessLevel = accessLevel; }

    public int getCostPerHour() { return costPerHour; }
    public void setCostPerHour(int costPerHour) { this.costPerHour = costPerHour; }
}
