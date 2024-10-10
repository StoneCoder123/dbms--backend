package com.proj.mideval.model;

public class Prescription {
    private int prescriptionID;
    private int treatmentID;
    private int medicineID;

    // Constructors
    public Prescription() {}

    public Prescription(int prescriptionID, int treatmentID, int medicineID) {
        this.prescriptionID = prescriptionID;
        this.treatmentID = treatmentID;
        this.medicineID = medicineID;
    }

    // Getters and Setters
    public int getPrescriptionID() { return prescriptionID; }
    public void setPrescriptionID(int prescriptionID) { this.prescriptionID = prescriptionID; }

    public int getTreatmentID() { return treatmentID; }
    public void setTreatmentID(int treatmentID) { this.treatmentID = treatmentID; }

    public int getMedicineID() { return medicineID; }
    public void setMedicineID(int medicineID) { this.medicineID = medicineID; }
}
