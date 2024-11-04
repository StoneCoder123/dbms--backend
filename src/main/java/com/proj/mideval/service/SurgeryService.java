package com.proj.mideval.service;

import com.proj.mideval.model.Surgery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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

    // Create a new Surgery record with a generated BillID
    public int createSurgery(Surgery surgery) {
        int billID = createBillForSurgery(surgery);  // Assume this creates a new bill and returns the billID
        String sql = "INSERT INTO Surgery (patientID, doctorID, billID, type, criticalLevel, time) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, surgery.getPatientID(), surgery.getDoctorID(), billID, surgery.getType(), surgery.getCriticalLevel(), surgery.getTime());
    }

    // Helper method to create a bill for the surgery (assuming Bill table has been defined)
    private int createBillForSurgery(Surgery surgery) {
        String sql = "INSERT INTO Bill (amount, dateCreated) VALUES (?, ?)";
        jdbcTemplate.update(sql, calculateSurgeryCost(surgery), LocalDateTime.now());

        // Retrieve the generated BillID
        String getBillIdSql = "SELECT LAST_INSERT_ID()";
        return jdbcTemplate.queryForObject(getBillIdSql, Integer.class);
    }

    // Calculate surgery cost (you can define the logic as needed)
    private int calculateSurgeryCost(Surgery surgery) {
        // Placeholder for cost calculation based on type, criticalLevel, etc.
        return 1000;  // Example static cost, adjust as necessary
    }

    // Delete a Surgery record by doctorID and surgeryID
    public int deleteSurgeryByDoctor(int surgeryID, int doctorID) {
        String sql = "DELETE FROM Surgery WHERE surgeryID = ? AND doctorID = ?";
        return jdbcTemplate.update(sql, surgeryID, doctorID);
    }

    // Reschedule Surgery by doctorID
    public int rescheduleSurgery(int surgeryID, int doctorID, LocalDateTime newTime) {
        String sql = "UPDATE Surgery SET time = ? WHERE surgeryID = ? AND doctorID = ?";
        return jdbcTemplate.update(sql, newTime, surgeryID, doctorID);
    }

    // Map a ResultSet row to a Surgery object
    private Surgery mapRowToSurgery(ResultSet rs) throws SQLException {
        Surgery surgery = new Surgery();
        surgery.setSurgeryID(rs.getInt("surgeryID"));
        surgery.setPatientID(rs.getInt("patientID"));
        surgery.setDoctorID(rs.getInt("doctorID"));
        surgery.setBillID(rs.getInt("billID"));
        surgery.setType(rs.getString("type"));
        surgery.setCriticalLevel(rs.getInt("criticalLevel"));
        surgery.setTime(rs.getTimestamp("time").toLocalDateTime());
        return surgery;
    }
}
