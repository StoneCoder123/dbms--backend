package com.proj.mideval.service;

import com.proj.mideval.model.HelpsIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class HelpsInService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to get all HelpsIn records
    public List<HelpsIn> getAllHelpsIn() {
        String sql = "SELECT * FROM HelpsIn";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToHelpsIn(rs));
    }

    // Method to get a specific HelpsIn entry by surgeryID and staffID
    public Optional<HelpsIn> getHelpsInById(int surgeryID, int staffID) {
        String sql = "SELECT * FROM HelpsIn WHERE SurgeryID = ? AND StaffID = ?";
        return jdbcTemplate.query(sql, new Object[]{surgeryID, staffID}, (rs, rowNum) -> mapRowToHelpsIn(rs)).stream().findFirst();
    }

    // Method to create a new HelpsIn record
    public int createHelpsIn(HelpsIn helpsIn) {
        String sql = "INSERT INTO HelpsIn (SurgeryID, StaffID, Rating) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, helpsIn.getSurgeryID(), helpsIn.getStaffID(), helpsIn.getRating());
    }

    // Method to update an existing HelpsIn record
    public int updateHelpsIn(int surgeryID, int staffID, HelpsIn helpsIn) {
        String sql = "UPDATE HelpsIn SET Rating = ? WHERE SurgeryID = ? AND StaffID = ?";
        return jdbcTemplate.update(sql, helpsIn.getRating(), surgeryID, staffID);
    }

    // Method to delete a HelpsIn record
    public int deleteHelpsIn(int surgeryID, int staffID) {
        String sql = "DELETE FROM HelpsIn WHERE SurgeryID = ? AND StaffID = ?";
        return jdbcTemplate.update(sql, surgeryID, staffID);
    }

    // Method to map a ResultSet row to a HelpsIn object
    private HelpsIn mapRowToHelpsIn(ResultSet rs) throws SQLException {
        HelpsIn helpsIn = new HelpsIn();
        helpsIn.setSurgeryID(rs.getInt("SurgeryID"));
        helpsIn.setStaffID(rs.getInt("StaffID"));
        helpsIn.setRating(rs.getInt("Rating"));
        return helpsIn;
    }
}
