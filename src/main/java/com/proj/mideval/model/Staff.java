package com.proj.mideval.model;

import java.util.Date;

public class Staff {
    private int staffID;
    private String firstName;
    private String lastName;
    private Date dob;
    private String education;
    private String gender;
    private String post;
    private String email;
    private String phone;

    // Constructors
    public Staff() {}

    public Staff(int staffID, String firstName, String lastName, Date dob, String education, String gender, String post, String email, String phone) {
        this.staffID = staffID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.education = education;
        this.gender = gender;
        this.post = post;
        this.email = email;
        this.phone = phone;
    }

    // Getters and Setters
    public int getStaffID() { return staffID; }
    public void setStaffID(int staffID) { this.staffID = staffID; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }

    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getPost() { return post; }
    public void setPost(String post) { this.post = post; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
