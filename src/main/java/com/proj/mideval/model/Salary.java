package com.proj.mideval.model;

public class Salary {
    private String workerID;
    private int amount;

    // Constructors
    public Salary() {}

    public Salary(String workerID, int amount) {
        this.workerID = workerID;
        this.amount = amount;
    }

    // Getters and Setters
    public String getWorkerID() {
        return workerID;
    }

    public void setWorkerID(String workerID) {
        this.workerID = workerID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
