package com.proj.mideval.service;

import com.proj.mideval.model.BuysMedicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class BuysMedicineService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to get all medicine purchases
    public List<BuysMedicine> getAllBuysMedicines() {
        String sql = "SELECT * FROM Buys_Medicine";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToBuysMedicine(rs));
    }

    // Method to get a specific purchase by patient ID and chemist ID
    public Optional<BuysMedicine> getBuysMedicineById(int patientID, int chemistID) {
        String sql = "SELECT * FROM Buys_Medicine WHERE PatientID = ? AND ChemistID = ?";
        return jdbcTemplate.query(sql, new Object[]{patientID, chemistID}, (rs, rowNum) -> mapRowToBuysMedicine(rs)).stream().findFirst();
    }

    // Method to create a new purchase record
    public int createBuysMedicine(BuysMedicine buysMedicine) {
        String sql = "INSERT INTO Buys_Medicine (PatientID, ChemistID, Date) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, buysMedicine.getPatientID(), buysMedicine.getChemistID(), buysMedicine.getDate());
    }

    // Method to update an existing purchase record
    public int updateBuysMedicine(int patientID, int chemistID, BuysMedicine buysMedicine) {
        String sql = "UPDATE Buys_Medicine SET Date = ? WHERE PatientID = ? AND ChemistID = ?";
        return jdbcTemplate.update(sql, buysMedicine.getDate(), patientID, chemistID);
    }

    // Method to delete a purchase record
    public int deleteBuysMedicine(int patientID, int chemistID) {
        String sql = "DELETE FROM Buys_Medicine WHERE PatientID = ? AND ChemistID = ?";
        return jdbcTemplate.update(sql, patientID, chemistID);
    }

    // Method to map a ResultSet row to a BuysMedicine object
    private BuysMedicine mapRowToBuysMedicine(ResultSet rs) throws SQLException {
        BuysMedicine buysMedicine = new BuysMedicine();
        buysMedicine.setPatientID(rs.getInt("PatientID"));
        buysMedicine.setChemistID(rs.getInt("ChemistID"));
        buysMedicine.setDate(rs.getDate("Date"));
        return buysMedicine;
    }
}
