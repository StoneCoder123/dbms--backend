package com.proj.mideval.model;

public class ApprovesExpense {
    private int adminID;
    private int deptID;
    private String type;
    private int amount;

    // Constructors
    public ApprovesExpense() {}

    public ApprovesExpense(int adminID, int deptID, String type, int amount) {
        this.adminID = adminID;
        this.deptID = deptID;
        this.type = type;
        this.amount = amount;
    }

    // Getters and Setters
    public int getAdminID() { return adminID; }
    public void setAdminID(int adminID) { this.adminID = adminID; }

    public int getDeptID() { return deptID; }
    public void setDeptID(int deptID) { this.deptID = deptID; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
}
