package com.proj.mideval.service;

import com.proj.mideval.model.MachineHiring;
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
public class MachineHiringService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Retrieve all MachineHiring records
    public List<MachineHiring> getAllMachineHirings() {
        String sql = "SELECT * FROM MachineHiring";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToMachineHiring(rs));
    }

    // Retrieve a MachineHiring record by hiringID
    public Optional<MachineHiring> getMachineHiringById(int hiringID) {
        String sql = "SELECT * FROM MachineHiring WHERE hiringID = ?";
        return jdbcTemplate.query(sql, new Object[]{hiringID}, (rs, rowNum) -> mapRowToMachineHiring(rs)).stream().findFirst();
    }

    // Retrieve all MachineHiring records by doctorID
    public List<MachineHiring> getMachineHiringByDoctorID(int doctorID) {
        String sql = "SELECT * FROM MachineHiring WHERE doctorID = ?";
        return jdbcTemplate.query(sql, new Object[]{doctorID}, (rs, rowNum) -> mapRowToMachineHiring(rs));
    }

    // Create a new MachineHiring record
    public int createMachineHiring(int doctorID, int machineID, Date startDate, Date endDate) {
        String sql = "INSERT INTO MachineHiring (doctorID, machineID, startDate, endDate) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, doctorID);
            ps.setInt(2, machineID);
            ps.setTimestamp(3, new Timestamp(startDate.getTime()));  // Convert Date to SQL Timestamp
            ps.setTimestamp(4, new Timestamp(endDate.getTime()));    // Convert Date to SQL Timestamp
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue(); // Return the generated hiringID
    }

    // Delete a MachineHiring record by hiringID and doctorID
    public int deleteMachineHiringByDoctor(int hiringID, int doctorID) {
        String sql = "DELETE FROM MachineHiring WHERE hiringID = ? AND doctorID = ?";
        return jdbcTemplate.update(sql, hiringID, doctorID);
    }

    // Extend or reschedule MachineHiring endDate by doctorID
    public int extendMachineHiring(int hiringID, int doctorID, Date newEndDate) {
        String sql = "UPDATE MachineHiring SET endDate = ? WHERE hiringID = ? AND doctorID = ?";
        return jdbcTemplate.update(sql, new Timestamp(newEndDate.getTime()), hiringID, doctorID);
    }

    // Map a ResultSet row to a MachineHiring object
    private MachineHiring mapRowToMachineHiring(ResultSet rs) throws SQLException {
        MachineHiring machineHiring = new MachineHiring();
        machineHiring.setHiringID(rs.getInt("hiringID"));
        machineHiring.setDoctorID(rs.getInt("doctorID"));
        machineHiring.setMachineID(rs.getInt("machineID"));
        machineHiring.setStartDate(rs.getTimestamp("startDate"));  // Directly use Timestamp as Date
        machineHiring.setEndDate(rs.getTimestamp("endDate"));      // Directly use Timestamp as Date
        return machineHiring;
    }

    public int addMachineHiringForDoctor(int doctorID, int machineID, Date startDate, Date endDate) {
        // You could add additional checks to ensure the doctor doesn't already have an active machine hiring for the same machine
        String sql = "INSERT INTO MachineHiring (doctorID, machineID, startDate, endDate) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, doctorID);
            ps.setInt(2, machineID);
            ps.setTimestamp(3, new Timestamp(startDate.getTime()));  // Convert Date to SQL Timestamp
            ps.setTimestamp(4, new Timestamp(endDate.getTime()));    // Convert Date to SQL Timestamp
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue(); // Return the generated hiringID
    }
}
