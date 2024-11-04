package com.proj.mideval.model;

public class Room {
    private int roomID;
    private String roomType;
    private int cost;

    // Constructors
    public Room() {}

    public Room(int roomID, String roomType, int cost) {
        this.roomID = roomID;
        this.roomType = roomType;
        this.cost = cost;
    }

    // Getters and Setters
    public int getRoomID() { return roomID; }
    public void setRoomID(int roomID) { this.roomID = roomID; }

    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }

    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; }
}
