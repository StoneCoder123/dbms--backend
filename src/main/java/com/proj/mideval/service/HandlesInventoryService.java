package com.proj.mideval.service;

import com.proj.mideval.model.HandlesInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class HandlesInventoryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to get all HandlesInventory records
    public List<HandlesInventory> getAllHandlesInventory() {
        String sql = "SELECT * FROM HandlesInventory";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToHandlesInventory(rs));
    }

    // Method to get a specific HandlesInventory entry by medicineID and chemistID
    public Optional<HandlesInventory> getHandlesInventoryById(int medicineID, int chemistID) {
        String sql = "SELECT * FROM HandlesInventory WHERE MedicineID = ? AND ChemistID = ?";
        return jdbcTemplate.query(sql, new Object[]{medicineID, chemistID}, (rs, rowNum) -> mapRowToHandlesInventory(rs)).stream().findFirst();
    }

    // Method to create a new HandlesInventory record
    public int createHandlesInventory(HandlesInventory handlesInventory) {
        String sql = "INSERT INTO HandlesInventory (MedicineID, ChemistID) VALUES (?, ?)";
        return jdbcTemplate.update(sql, handlesInventory.getMedicineID(), handlesInventory.getChemistID());
    }

    // Method to update an existing HandlesInventory record
    public int updateHandlesInventory(int medicineID, int chemistID, HandlesInventory handlesInventory) {
        String sql = "UPDATE HandlesInventory SET ChemistID = ? WHERE MedicineID = ?";
        return jdbcTemplate.update(sql, handlesInventory.getChemistID(), medicineID);
    }

    // Method to delete a HandlesInventory record
    public int deleteHandlesInventory(int medicineID, int chemistID) {
        String sql = "DELETE FROM HandlesInventory WHERE MedicineID = ? AND ChemistID = ?";
        return jdbcTemplate.update(sql, medicineID, chemistID);
    }

    // Method to map a ResultSet row to a HandlesInventory object
    private HandlesInventory mapRowToHandlesInventory(ResultSet rs) throws SQLException {
        HandlesInventory handlesInventory = new HandlesInventory();
        handlesInventory.setMedicineID(rs.getInt("MedicineID"));
        handlesInventory.setChemistID(rs.getInt("ChemistID"));
        return handlesInventory;
    }
}
