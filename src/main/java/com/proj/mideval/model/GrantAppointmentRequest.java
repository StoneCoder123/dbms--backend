package com.proj.mideval.model;

import java.util.Date;

public class GrantAppointmentRequest {
    private int appointmentID;
    private Date appointmentTime;
    private int cost;

    // Getters and Setters
    public int getAppointmentID() {
        return appointmentID;
    }
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }
    public Date getAppointmentTime() {
        return appointmentTime;
    }
    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
}
