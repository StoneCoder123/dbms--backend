package com.proj.mideval.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class RoomBookingRequest {
    private int roomID;
    private int patientID;
    private Date bookFrom;
    private Date bookTill;
    private int numDays;
    private int totalCost;

    // Getters and Setters
    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public Date getBookFrom() {
        return bookFrom;
    }

    public void setBookFrom(Date bookFrom) {
        this.bookFrom = bookFrom;
    }

    public Date getBookTill() {
        return bookTill;
    }

    public void setBookTill(Date bookTill) {
        this.bookTill = bookTill;
    }

    public int getNumDays() {
        return numDays;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}

