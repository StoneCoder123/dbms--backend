package com.proj.mideval.service;

import com.proj.mideval.model.TakesChemistSal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class TakesChemistSalService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Retrieve all chemist salary records
    public List<TakesChemistSal> getAllChemistSalaries() {
        String sql = "SELECT * FROM TakesChemistSal";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToTakesChemistSal(rs));
    }

    // Retrieve a chemist salary record by ID
    public Optional<TakesChemistSal> getChemistSalaryById(int csID) {
        String sql = "SELECT * FROM TakesChemistSal WHERE csID = ?";
        return jdbcTemplate.query(sql, new Object[]{csID}, (rs, rowNum) -> mapRowToTakesChemistSal(rs)).stream().findFirst();
    }

    public List<TakesChemistSal> getChemistSalaryByChemistID(int chemistID) {
        String sql = "SELECT * FROM TakesChemistSal WHERE chemistID = ?";
        return jdbcTemplate.query(sql, new Object[]{chemistID}, (rs, rowNum) -> mapRowToTakesChemistSal(rs));
    }

    // Create a new chemist salary record
    public int createChemistSalary(TakesChemistSal chemistSal) {
        String sql = "INSERT INTO TakesChemistSal (chemistID, salary, issueDate) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, chemistSal.getChemistID(), chemistSal.getSalary(), chemistSal.getIssueDate());
    }

    // Update an existing chemist salary record
    public int updateChemistSalary(int csID, TakesChemistSal chemistSal) {
        String sql = "UPDATE TakesChemistSal SET chemistID = ?, salary = ?, issueDate = ? WHERE csID = ?";
        return jdbcTemplate.update(sql, chemistSal.getChemistID(), chemistSal.getSalary(), chemistSal.getIssueDate(), csID);
    }

    // Delete a chemist salary record by ID
    public int deleteChemistSalary(int csID) {
        String sql = "DELETE FROM TakesChemistSal WHERE csID = ?";
        return jdbcTemplate.update(sql, csID);
    }

    // Map a ResultSet row to a TakesChemistSal object
    private TakesChemistSal mapRowToTakesChemistSal(ResultSet rs) throws SQLException {
        TakesChemistSal chemistSal = new TakesChemistSal();
        chemistSal.setCsID(rs.getInt("csID"));
        chemistSal.setChemistID(rs.getInt("chemistID"));
        chemistSal.setSalary(rs.getInt("salary"));
        chemistSal.setIssueDate(rs.getDate("issueDate"));
        return chemistSal;
    }
}
