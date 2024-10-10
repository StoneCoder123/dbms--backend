package com.proj.mideval.model;

public class Room {
    private int roomID;
    private String roomType;
    private int cost;
    private int patientID;

    // Constructors
    public Room() {}

    public Room(int roomID, String roomType, int cost, int patientID) {
        this.roomID = roomID;
        this.roomType = roomType;
        this.cost = cost;
        this.patientID = patientID;
    }

    // Getters and Setters
    public int getRoomID() { return roomID; }
    public void setRoomID(int roomID) { this.roomID = roomID; }

    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }

    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; }

    public int getPatientID() { return patientID; }
    public void setPatientID(int patientID) { this.patientID = patientID; }
}
