package com.proj.mideval.model;

public class HandlesInventory {
    private int medicineID;
    private int chemistID;

    // Constructors
    public HandlesInventory() {}

    public HandlesInventory(int medicineID, int chemistID) {
        this.medicineID = medicineID;
        this.chemistID = chemistID;
    }

    // Getters and Setters
    public int getMedicineID() { return medicineID; }
    public void setMedicineID(int medicineID) { this.medicineID = medicineID; }

    public int getChemistID() { return chemistID; }
    public void setChemistID(int chemistID) { this.chemistID = chemistID; }
}
