package com.proj.mideval.model;

import java.util.Date;

public class Chemist {
    private int chemistID;
    private String firstName;
    private String lastName;
    private Date dob;
    private String gender;
    private String email;
    private String phone;

    // Constructors
    public Chemist() {}

    public Chemist(int chemistID, String firstName, String lastName, Date dob, String gender, String email, String phone) {
        this.chemistID = chemistID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
    }

    // Getters and Setters
    public int getChemistID() { return chemistID; }
    public void setChemistID(int chemistID) { this.chemistID = chemistID; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}

