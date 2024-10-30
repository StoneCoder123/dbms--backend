package com.proj.mideval.model;

public class Admin {
    private int adminID;
    private int doctorID;
    private String accessLevel;
    private String password;

    // Constructors
    public Admin() {}

    public Admin(int adminID, int doctorID, String accessLevel,String password) {
        this.adminID = adminID;
        this.doctorID = doctorID;
        this.accessLevel = accessLevel;
        this.password = password;
    }

    // Getters and Setters
    public int getAdminID() { return adminID; }
    public void setAdminID(int adminID) { this.adminID = adminID; }

    public int getDoctorID() { return doctorID; }
    public void setDoctorID(int doctorID) { this.doctorID = doctorID; }

    public String getAccessLevel() { return accessLevel; }
    public void setAccessLevel(String accessLevel) { this.accessLevel = accessLevel; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
