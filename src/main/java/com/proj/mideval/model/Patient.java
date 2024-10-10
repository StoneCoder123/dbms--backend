package com.proj.mideval.model;
import java.util.Date;

public class Patient {
    private int patientID;
    private String firstName;
    private String lastName;
    private String address;
    private String NTK;
    private String email;
    private String gender;
    private String history;
    private Date dob;

    // Constructors
    public Patient() {}

    public Patient(int patientID, String firstName, String lastName, String address, String NTK, String email, String gender, String history, Date dob) {
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.NTK = NTK;
        this.email = email;
        this.gender = gender;
        this.history = history;
        this.dob = dob;
    }

    // Getters and Setters
    public int getPatientID() { return patientID; }
    public void setPatientID(int patientID) { this.patientID = patientID; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getNTK() { return NTK; }
    public void setNTK(String NTK) { this.NTK = NTK; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getHistory() { return history; }
    public void setHistory(String history) { this.history = history; }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }
}
