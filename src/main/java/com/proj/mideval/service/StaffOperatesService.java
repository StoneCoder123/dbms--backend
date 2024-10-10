package com.proj.mideval.service;

import com.proj.mideval.model.StaffOperates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class StaffOperatesService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Retrieve all StaffOperates records
    public List<StaffOperates> getAllStaffOperates() {
        String sql = "SELECT * FROM StaffOperates";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToStaffOperates(rs));
    }

    // Retrieve a StaffOperates record by machineID and staffID
    public Optional<StaffOperates> getStaffOperatesByIds(int machineID, int staffID) {
        String sql = "SELECT * FROM StaffOperates WHERE machineID = ? AND staffID = ?";
        return jdbcTemplate.query(sql, new Object[]{machineID, staffID}, (rs, rowNum) -> mapRowToStaffOperates(rs)).stream().findFirst();
    }

    // Create a new StaffOperates record
    public int createStaffOperates(StaffOperates staffOperates) {
        String sql = "INSERT INTO StaffOperates (machineID, staffID) VALUES (?, ?)";
        return jdbcTemplate.update(sql, staffOperates.getMachineID(), staffOperates.getStaffID());
    }

    // Delete a StaffOperates record by machineID and staffID
    public int deleteStaffOperates(int machineID, int staffID) {
        String sql = "DELETE FROM StaffOperates WHERE machineID = ? AND staffID = ?";
        return jdbcTemplate.update(sql, machineID, staffID);
    }

    // Map a ResultSet row to a StaffOperates object
    private StaffOperates mapRowToStaffOperates(ResultSet rs) throws SQLException {
        StaffOperates staffOperates = new StaffOperates();
        staffOperates.setMachineID(rs.getInt("machineID"));
        staffOperates.setStaffID(rs.getInt("staffID"));
        return staffOperates;
    }
}
