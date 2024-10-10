package com.proj.mideval.service;

import com.proj.mideval.model.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to get all Prescription records
    public List<Prescription> getAllPrescriptions() {
        String sql = "SELECT * FROM Prescription";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToPrescription(rs));
    }

    // Method to get a specific Prescription entry by prescriptionID
    public Optional<Prescription> getPrescriptionById(int prescriptionID) {
        String sql = "SELECT * FROM Prescription WHERE PrescriptionID = ?";
        return jdbcTemplate.query(sql, new Object[]{prescriptionID}, (rs, rowNum) -> mapRowToPrescription(rs)).stream().findFirst();
    }

    // Method to create a new Prescription record
    public int createPrescription(Prescription prescription) {
        String sql = "INSERT INTO Prescription (PrescriptionID, TreatmentID, MedicineID) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, prescription.getPrescriptionID(), prescription.getTreatmentID(), prescription.getMedicineID());
    }

    // Method to update an existing Prescription record
    public int updatePrescription(int prescriptionID, Prescription prescription) {
        String sql = "UPDATE Prescription SET TreatmentID = ?, MedicineID = ? WHERE PrescriptionID = ?";
        return jdbcTemplate.update(sql, prescription.getTreatmentID(), prescription.getMedicineID(), prescriptionID);
    }

    // Method to delete a Prescription record
    public int deletePrescription(int prescriptionID) {
        String sql = "DELETE FROM Prescription WHERE PrescriptionID = ?";
        return jdbcTemplate.update(sql, prescriptionID);
    }

    // Method to map a ResultSet row to a Prescription object
    private Prescription mapRowToPrescription(ResultSet rs) throws SQLException {
        Prescription prescription = new Prescription();
        prescription.setPrescriptionID(rs.getInt("PrescriptionID"));
        prescription.setTreatmentID(rs.getInt("TreatmentID"));
        prescription.setMedicineID(rs.getInt("MedicineID"));
        return prescription;
    }
}
