package com.proj.mideval.service;

import com.proj.mideval.model.TakesCare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class TakesCareService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Retrieve all TakesCare records
    public List<TakesCare> getAllTakesCare() {
        String sql = "SELECT * FROM TakesCare";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToTakesCare(rs));
    }

    // Retrieve a TakesCare record by patientID
    public Optional<TakesCare> getTakesCareByPatientId(int patientID) {
        String sql = "SELECT * FROM TakesCare WHERE patientID = ?";
        return jdbcTemplate.query(sql, new Object[]{patientID}, (rs, rowNum) -> mapRowToTakesCare(rs)).stream().findFirst();
    }

    // Create a new TakesCare record
    public int createTakesCare(TakesCare takesCare) {
        String sql = "INSERT INTO TakesCare (patientID, staffID, rating, feedback) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, takesCare.getPatientID(), takesCare.getStaffID(), takesCare.getRating(), takesCare.getFeedback());
    }

    // Delete a TakesCare record by patientID
    public int deleteTakesCare(int patientID) {
        String sql = "DELETE FROM TakesCare WHERE patientID = ?";
        return jdbcTemplate.update(sql, patientID);
    }

    // Map a ResultSet row to a TakesCare object
    private TakesCare mapRowToTakesCare(ResultSet rs) throws SQLException {
        TakesCare takesCare = new TakesCare();
        takesCare.setPatientID(rs.getInt("patientID"));
        takesCare.setStaffID(rs.getInt("staffID"));
        takesCare.setRating(rs.getInt("rating"));
        takesCare.setFeedback(rs.getString("feedback"));
        return takesCare;
    }
}
