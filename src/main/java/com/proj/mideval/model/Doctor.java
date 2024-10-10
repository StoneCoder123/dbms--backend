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
    private String mail;
    private String post;
    private String department;
    private String specialization;

    // Constructors
    public Doctor() {}

    public Doctor(int doctorID, String firstName, String lastName, Date dob, String education, String gender, String phone, String mail, String post, String department, String specialization) {
        this.doctorID = doctorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.education = education;
        this.gender = gender;
        this.phone = phone;
        this.mail = mail;
        this.post = post;
        this.department = department;
        this.specialization = specialization;
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

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getPost() { return post; }
    public void setPost(String post) { this.post = post; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
}
