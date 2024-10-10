package com.proj.mideval.model;

public class AppointDoctor {
    private int adminID;
    private int doctorID;
    private String post;
    private String department;
    private int salary;

    // Constructors
    public AppointDoctor() {}

    public AppointDoctor(int adminID, int doctorID, String post, String department, int salary) {
        this.adminID = adminID;
        this.doctorID = doctorID;
        this.post = post;
        this.department = department;
        this.salary = salary;
    }

    // Getters and Setters
    public int getAdminID() { return adminID; }
    public void setAdminID(int adminID) { this.adminID = adminID; }

    public int getDoctorID() { return doctorID; }
    public void setDoctorID(int doctorID) { this.doctorID = doctorID; }

    public String getPost() { return post; }
    public void setPost(String post) { this.post = post; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public int getSalary() { return salary; }
    public void setSalary(int salary) { this.salary = salary; }
}
