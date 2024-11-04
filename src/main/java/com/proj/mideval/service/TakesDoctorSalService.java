package com.proj.mideval.service;

import com.proj.mideval.model.TakesDoctorSal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class TakesDoctorSalService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Retrieve all doctor salary records
    public List<TakesDoctorSal> getAllDoctorSalaries() {
        String sql = "SELECT * FROM TakesDoctorSal";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToTakesDoctorSal(rs));
    }

    // Retrieve a doctor salary record by ID
    public Optional<TakesDoctorSal> getDoctorSalaryById(int dsID) {
        String sql = "SELECT * FROM TakesDoctorSal WHERE dsID = ?";
        return jdbcTemplate.query(sql, new Object[]{dsID}, (rs, rowNum) -> mapRowToTakesDoctorSal(rs)).stream().findFirst();
    }

    public List<TakesDoctorSal> getDoctorSalaryByDoctorID(int doctorID) {
        String sql = "SELECT * FROM TakesDoctorSal WHERE doctorID = ?";
        return jdbcTemplate.query(sql, new Object[]{doctorID}, (rs, rowNum) -> mapRowToTakesDoctorSal(rs));
    }

    // Create a new doctor salary record
    public int createDoctorSalary(TakesDoctorSal doctorSal) {
        String sql = "INSERT INTO TakesDoctorSal (doctorID, salary, issueDate) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, doctorSal.getDoctorID(), doctorSal.getSalary(), doctorSal.getIssueDate());
    }

    // Update an existing doctor salary record
    public int updateDoctorSalary(int dsID, TakesDoctorSal doctorSal) {
        String sql = "UPDATE TakesDoctorSal SET doctorID = ?, salary = ?, issueDate = ? WHERE dsID = ?";
        return jdbcTemplate.update(sql, doctorSal.getDoctorID(), doctorSal.getSalary(), doctorSal.getIssueDate(), dsID);
    }

    // Delete a doctor salary record by ID
    public int deleteDoctorSalary(int dsID) {
        String sql = "DELETE FROM TakesDoctorSal WHERE dsID = ?";
        return jdbcTemplate.update(sql, dsID);
    }

    // Map a ResultSet row to a TakesDoctorSal object
    private TakesDoctorSal mapRowToTakesDoctorSal(ResultSet rs) throws SQLException {
        TakesDoctorSal doctorSal = new TakesDoctorSal();
        doctorSal.setDsID(rs.getInt("dsID"));
        doctorSal.setDoctorID(rs.getInt("doctorID"));
        doctorSal.setSalary(rs.getInt("salary"));
        doctorSal.setIssueDate(rs.getDate("issueDate"));
        return doctorSal;
    }
}
