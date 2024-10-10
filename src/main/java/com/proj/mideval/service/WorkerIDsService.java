package com.proj.mideval.service;

import com.proj.mideval.model.WorkerIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerIDsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Retrieve all WorkerIDs records
    public List<WorkerIDs> getAllWorkerIDs() {
        String sql = "SELECT * FROM WorkerIDs";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToWorkerIDs(rs));
    }

    // Retrieve a WorkerIDs record by workerID
    public Optional<WorkerIDs> getWorkerIDsByWorkerId(String workerID) {
        String sql = "SELECT * FROM WorkerIDs WHERE workerID = ?";
        return jdbcTemplate.query(sql, new Object[]{workerID}, (rs, rowNum) -> mapRowToWorkerIDs(rs)).stream().findFirst();
    }

    // Create a new WorkerIDs record
    public int createWorkerIDs(WorkerIDs workerIDs) {
        String sql = "INSERT INTO WorkerIDs (doctorID, staffID, chemistID, workerID) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, workerIDs.getDoctorID(), workerIDs.getStaffID(), workerIDs.getChemistID(), workerIDs.getWorkerID());
    }

    // Delete a WorkerIDs record by workerID
    public int deleteWorkerIDs(String workerID) {
        String sql = "DELETE FROM WorkerIDs WHERE workerID = ?";
        return jdbcTemplate.update(sql, workerID);
    }

    // Map a ResultSet row to a WorkerIDs object
    private WorkerIDs mapRowToWorkerIDs(ResultSet rs) throws SQLException {
        WorkerIDs workerIDs = new WorkerIDs();
        workerIDs.setDoctorID(rs.getInt("doctorID"));
        workerIDs.setStaffID(rs.getInt("staffID"));
        workerIDs.setChemistID(rs.getInt("chemistID"));
        workerIDs.setWorkerID(rs.getString("workerID"));
        return workerIDs;
    }
}
