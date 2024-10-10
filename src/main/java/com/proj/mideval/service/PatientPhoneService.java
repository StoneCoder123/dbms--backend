package com.proj.mideval.service;

import com.proj.mideval.model.PatientPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class PatientPhoneService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to get all PatientPhone records
    public List<PatientPhone> getAllPatientPhones() {
        String sql = "SELECT * FROM PatientPhone";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToPatientPhone(rs));
    }

    // Method to get a specific PatientPhone entry by patientID
    public Optional<PatientPhone> getPatientPhoneById(int patientID) {
        String sql = "SELECT * FROM PatientPhone WHERE PatientID = ?";
        return jdbcTemplate.query(sql, new Object[]{patientID}, (rs, rowNum) -> mapRowToPatientPhone(rs)).stream().findFirst();
    }

    // Method to create a new PatientPhone record
    public int createPatientPhone(PatientPhone patientPhone) {
        String sql = "INSERT INTO PatientPhone (Phone, PatientID) VALUES (?, ?)";
        return jdbcTemplate.update(sql, patientPhone.getPhone(), patientPhone.getPatientID());
    }

    // Method to update an existing PatientPhone record
    public int updatePatientPhone(int patientID, PatientPhone patientPhone) {
        String sql = "UPDATE PatientPhone SET Phone = ? WHERE PatientID = ?";
        return jdbcTemplate.update(sql, patientPhone.getPhone(), patientID);
    }

    // Method to delete a PatientPhone record
    public int deletePatientPhone(int patientID) {
        String sql = "DELETE FROM PatientPhone WHERE PatientID = ?";
        return jdbcTemplate.update(sql, patientID);
    }

    // Method to map a ResultSet row to a PatientPhone object
    private PatientPhone mapRowToPatientPhone(ResultSet rs) throws SQLException {
        PatientPhone patientPhone = new PatientPhone();
        patientPhone.setPhone(rs.getString("Phone"));
        patientPhone.setPatientID(rs.getInt("PatientID"));
        return patientPhone;
    }
}
