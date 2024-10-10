package com.proj.mideval.model;

public class Medicines {
    private int medicineID;
    private String medicineName;
    private int cost;
    private String type;
    private String companyName;

    // Constructors
    public Medicines() {}

    public Medicines(int medicineID, String medicineName, int cost, String type, String companyName) {
        this.medicineID = medicineID;
        this.medicineName = medicineName;
        this.cost = cost;
        this.type = type;
        this.companyName = companyName;
    }

    // Getters and Setters
    public int getMedicineID() { return medicineID; }
    public void setMedicineID(int medicineID) { this.medicineID = medicineID; }

    public String getMedicineName() { return medicineName; }
    public void setMedicineName(String medicineName) { this.medicineName = medicineName; }

    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
}

