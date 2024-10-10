package com.proj.mideval.model;

public class AppointChemist {
    private int adminID;
    private int chemistID;
    private String remarks;
    private String strengths;
    private String weakness;

    // Constructors
    public AppointChemist() {}

    public AppointChemist(int adminID, int chemistID, String remarks, String strengths, String weakness) {
        this.adminID = adminID;
        this.chemistID = chemistID;
        this.remarks = remarks;
        this.strengths = strengths;
        this.weakness = weakness;
    }

    // Getters and Setters
    public int getAdminID() { return adminID; }
    public void setAdminID(int adminID) { this.adminID = adminID; }

    public int getChemistID() { return chemistID; }
    public void setChemistID(int chemistID) { this.chemistID = chemistID; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getStrengths() { return strengths; }
    public void setStrengths(String strengths) { this.strengths = strengths; }

    public String getWeakness() { return weakness; }
    public void setWeakness(String weakness) { this.weakness = weakness; }
}
