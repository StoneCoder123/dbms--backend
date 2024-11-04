package com.proj.mideval.model;

import java.util.Date;

public class TakesChemistSal {
    private int csID;
    private int chemistID;
    private int salary;
    private Date issueDate;

    public TakesChemistSal(){}

    public TakesChemistSal(int csID, int chemistID, int salary, Date issueDate){
        this.csID = csID;
        this.chemistID = chemistID;
        this.salary = salary;
        this.issueDate = issueDate;
    }

    public int getCsID() {
        return csID;
    }

    public void setCsID(int csID) {
        this.csID = csID;
    }

    public int getChemistID() {
        return chemistID;
    }

    public void setChemistID(int chemistID) {
        this.chemistID = chemistID;
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
