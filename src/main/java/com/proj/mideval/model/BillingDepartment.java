package com.proj.mideval.model;

public class BillingDepartment {
    private int deptID;
    private String name;
    private String phone;
    private String email;
    private int billID;

    // Constructors
    public BillingDepartment() {}

    public BillingDepartment(int deptID, String name, String phone, String email,
                              int billID) {
        this.deptID = deptID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.billID = billID;
    }

    // Getters and Setters
    public int getDeptID() { return deptID; }
    public void setDeptID(int deptID) { this.deptID = deptID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getBillID() { return billID; }
    public void setBillID(int billID) { this.billID = billID; }
}

