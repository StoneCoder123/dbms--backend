package com.proj.mideval.model;

import java.util.Date;

public class TakesDoctorSal {
    private int dsID;
    private int doctorID;
    private int salary;
    private Date issueDate;

    public TakesDoctorSal(){}

    public TakesDoctorSal(int dsID, int doctorID, int salary, Date issueDate){
        this.dsID = dsID;
        this.doctorID = doctorID;
        this.salary = salary;
        this.issueDate = issueDate;
    }

    public int getDsID() {
        return dsID;
    }

    public void setDsID(int dsID) {
        this.dsID = dsID;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
}
