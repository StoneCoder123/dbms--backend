package com.proj.mideval.model;

import java.util.Date;

public class MachineHiring {
    private int hiringID;
    private int doctorID;
    private int machineID;
    private Date startDate;
    private Date endDate;

    // Constructors
    public MachineHiring() {}

    public MachineHiring(int hiringID, int doctorID, int machineID, Date startDate, Date endDate) {
        this.hiringID = hiringID;
        this.doctorID = doctorID;
        this.machineID = machineID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    public int getHiringID() { return hiringID; }
    public void setHiringID(int hiringID) { this.hiringID = hiringID; }

    public int getDoctorID() { return doctorID; }
    public void setDoctorID(int doctorID) { this.doctorID = doctorID; }

    public int getMachineID() { return machineID; }
    public void setMachineID(int machineID) { this.machineID = machineID; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
}
