
package com.proj.mideval.service;

import com.proj.mideval.model.Surgery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SurgeryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BillService billService;

    // Retrieve all Surgery records (accessible to doctor and patient)
    public List<Surgery> getAllSurgeries() {
        String sql = "SELECT * FROM Surgery";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToSurgery(rs));
    }

    // Retrieve a Surgery record by surgeryID (accessible to doctor and patient)
    public Optional<Surgery> getSurgeryById(int surgeryID) {
        String sql = "SELECT * FROM Surgery WHERE surgeryID = ?";
        return jdbcTemplate.query(sql, new Object[]{surgeryID}, (rs, rowNum) -> mapRowToSurgery(rs)).stream().findFirst();
    }

    // Retrieve all Surgery records by doctorID (visible to doctor)
    public List<Surgery> getSurgeryByDoctorID(int doctorID) {
        String sql = "SELECT * FROM Surgery WHERE doctorID = ?";
        return jdbcTemplate.query(sql, new Object[]{doctorID}, (rs, rowNum) -> mapRowToSurgery(rs));
    }

    // Retrieve all Surgery records by patientID (visible to patient)
    public List<Surgery> getSurgeryByPatientID(int patientID) {
        String sql = "SELECT * FROM Surgery WHERE patientID = ?";
        return jdbcTemplate.query(sql, new Object[]{patientID}, (rs, rowNum) -> mapRowToSurgery(rs));
    }

    // Create a new Surgery record with an associated Bill created instantly
    public int createSurgery(int patientID, int doctorID, int totalCost, int criticalLevel, Date time) {
        // First, create a bill with type "Surgery"
        int billID = billService.createBill(patientID, totalCost, "Surgery");

        // SQL query to insert a new surgery record
        String sql = "INSERT INTO Surgery (PatientID, DoctorID, BillID, Type, CriticalLevel, Time) VALUES (?, ?, ?, 'Surgery', ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, patientID);
            ps.setInt(2, doctorID);
            ps.setInt(3, billID);  // Use the generated bill ID
            ps.setInt(4, criticalLevel);
            ps.setTimestamp(5, new Timestamp(time.getTime()));  // Convert Date to SQL Timestamp
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue(); // Return the generated surgery ID
    }

    // Delete a Surgery record by doctorID and surgeryID
    public int deleteSurgeryByDoctor(int surgeryID, int doctorID) {
        String sql = "DELETE FROM Surgery WHERE surgeryID = ? AND doctorID = ?";
        return jdbcTemplate.update(sql, surgeryID, doctorID);
    }

    // Reschedule Surgery by doctorID
    public int rescheduleSurgery(int surgeryID, int doctorID, Date newTime) {
        String sql = "UPDATE Surgery SET time = ? WHERE surgeryID = ? AND doctorID = ?";
        return jdbcTemplate.update(sql, new Timestamp(newTime.getTime()), surgeryID, doctorID);
    }

    // Calculate surgery cost (adjust logic as needed)
    private int calculateSurgeryCost(Surgery surgery) {
        // Basic cost calculation based on type and criticalLevel, modify as needed
        int baseCost = 1000;
        int criticalMultiplier = surgery.getCriticalLevel() * 200;
        return baseCost + criticalMultiplier;
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
        surgery.setTime(rs.getTimestamp("time"));  // Directly use Timestamp as Date
        return surgery;
    }
}

