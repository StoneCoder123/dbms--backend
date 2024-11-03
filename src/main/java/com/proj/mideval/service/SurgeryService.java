package com.proj.mideval.service;

import com.proj.mideval.model.Surgery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class SurgeryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Retrieve all Surgery records
    public List<Surgery> getAllSurgeries() {
        String sql = "SELECT * FROM Surgery";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToSurgery(rs));
    }

    // Retrieve a Surgery record by surgeryID
    public Optional<Surgery> getSurgeryById(int surgeryID) {
        String sql = "SELECT * FROM Surgery WHERE surgeryID = ?";
        return jdbcTemplate.query(sql, new Object[]{surgeryID}, (rs, rowNum) -> mapRowToSurgery(rs)).stream().findFirst();
    }

    // Retrieve all Surgery records by doctorID
    public List<Surgery> getSurgeryByDoctorID(int doctorID) {
        String sql = "SELECT * FROM Surgery WHERE doctorID = ?";
        return jdbcTemplate.query(sql, new Object[]{doctorID}, (rs, rowNum) -> mapRowToSurgery(rs));
    }

    // Retrieve all Surgery records by patientID
    public List<Surgery> getSurgeryByPatientID(int patientID) {
        String sql = "SELECT * FROM Surgery WHERE patientID = ?";
        return jdbcTemplate.query(sql, new Object[]{patientID}, (rs, rowNum) -> mapRowToSurgery(rs));
    }

    // Create a new Surgery record
    public int createSurgery(Surgery surgery) {
        String sql = "INSERT INTO Surgery (patientID, doctorID, type, criticalLevel, Cost) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, surgery.getPatientID(), surgery.getDoctorID(), surgery.getType(), surgery.getCriticalLevel(), surgery.getCost());
    }

    // Delete a Surgery record by surgeryID
    public int deleteSurgery(int surgeryID) {
        String sql = "DELETE FROM Surgery WHERE surgeryID = ?";
        return jdbcTemplate.update(sql, surgeryID);
    }

    // Map a ResultSet row to a Surgery object
    private Surgery mapRowToSurgery(ResultSet rs) throws SQLException {
        Surgery surgery = new Surgery();
        surgery.setSurgeryID(rs.getInt("surgeryID"));
        surgery.setPatientID(rs.getInt("patientID"));
        surgery.setDoctorID(rs.getInt("doctorID"));
        surgery.setType(rs.getString("type"));
        surgery.setCriticalLevel(rs.getInt("criticalLevel"));
        surgery.setCost(rs.getInt("cost"));
        return surgery;
    }
}
