package com.proj.mideval.model;
import java.util.Date;

public class Doctor {
    private int doctorID;
    private String firstName;
    private String lastName;
    private Date dob;
    private String education;
    private String gender;
    private String phone;
    private String email;
    private String post;
    private String department;
    private String specialization;
    private String password; // New password field

    // Constructors
    public Doctor() {}

    public Doctor(int doctorID, String firstName, String lastName, Date dob, String education, String gender, String phone, String email, String post, String department, String specialization, String password) {
        this.doctorID = doctorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.education = education;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.post = post;
        this.department = department;
        this.specialization = specialization;
        this.password = password; // New field assignment
    }

    // Getters and Setters
    public int getDoctorID() { return doctorID; }
    public void setDoctorID(int doctorID) { this.doctorID = doctorID; }

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

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = this.email; }

    public String getPost() { return post; }
    public void setPost(String post) { this.post = post; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
