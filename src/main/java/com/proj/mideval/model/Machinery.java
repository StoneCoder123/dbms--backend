package com.proj.mideval.model;

public class Machinery {
    private int machineID;
    private String name;
    private int cost;

    // Constructors
    public Machinery() {}

    public Machinery(int machineID, String name, int cost) {
        this.machineID = machineID;
        this.name = name;
        this.cost = cost;
    }

    // Getters and Setters
    public int getMachineID() { return machineID; }
    public void setMachineID(int machineID) { this.machineID = machineID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; }
}

