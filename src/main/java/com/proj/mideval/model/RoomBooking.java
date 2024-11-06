package com.proj.mideval.model;

import java.sql.Time;
import java.util.Date;

public class RoomBooking {
    private int roomBookingID;
    private int roomID;
    private int patientID;
    private Date bookFrom;
    private Date bookTill;
    private int billID;
    private int numDays;

    public RoomBooking() {}

    public RoomBooking(int roomBookingID, int roomID, int patientID, java.sql.Date bookFrom, java.sql.Date bookTill, int billID, int numDays) {
        this.roomBookingID = roomBookingID;
        this.roomID = roomID;
        this.patientID = patientID;
        this.bookFrom =  bookFrom;
        this.bookTill = bookTill;
        this.billID = billID;
        this.numDays = numDays;
    }

    public int getRoomBookingID() {
        return roomBookingID;
    }

    public void setRoomBookingID(int roomBookingID) {
        this.roomBookingID = roomBookingID;
    }

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

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getNumDays() {
        return numDays;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }
}
