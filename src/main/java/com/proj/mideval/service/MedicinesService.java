package com.proj.mideval.service;

import com.proj.mideval.model.Medicines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class MedicinesService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to get all Medicines records
    public List<Medicines> getAllMedicines() {
        String sql = "SELECT * FROM Medicines";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToMedicines(rs));
    }

    // Method to get a specific Medicine entry by medicineID
    public Optional<Medicines> getMedicinesById(int medicineID) {
        String sql = "SELECT * FROM Medicines WHERE MedicineID = ?";
        return jdbcTemplate.query(sql, new Object[]{medicineID}, (rs, rowNum) -> mapRowToMedicines(rs)).stream().findFirst();
    }

    // Method to create a new Medicines record
    public int createMedicines(Medicines medicines) {
        String sql = "INSERT INTO Medicines (MedicineName, Cost, Type, CompanyName) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, medicines.getMedicineName(), medicines.getCost(), medicines.getType(), medicines.getCompanyName());
    }

    // Method to update an existing Medicines record
    public int updateMedicines(int medicineID, Medicines medicines) {
        String sql = "UPDATE Medicines SET MedicineName = ?, Cost = ?, Type = ?, CompanyName = ? WHERE MedicineID = ?";
        return jdbcTemplate.update(sql, medicines.getMedicineName(), medicines.getCost(), medicines.getType(), medicines.getCompanyName(), medicineID);
    }

    // Method to delete a Medicines record
    public int deleteMedicines(int medicineID) {
        String sql = "DELETE FROM Medicines WHERE MedicineID = ?";
        return jdbcTemplate.update(sql, medicineID);
    }

    // Method to map a ResultSet row to a Medicines object
    private Medicines mapRowToMedicines(ResultSet rs) throws SQLException {
        Medicines medicines = new Medicines();
        medicines.setMedicineID(rs.getInt("MedicineID"));
        medicines.setMedicineName(rs.getString("MedicineName"));
        medicines.setCost(rs.getInt("Cost"));
        medicines.setType(rs.getString("Type"));
        medicines.setCompanyName(rs.getString("CompanyName"));
        medicines.setAmount(rs.getInt("Amount"));
        return medicines;
    }
}
