package com.proj.mideval.service;

import com.proj.mideval.model.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class SalaryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to get all Salary records
    public List<Salary> getAllSalaries() {
        String sql = "SELECT * FROM Salary";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToSalary(rs));
    }

    // Method to get a specific Salary entry by workerID
    public Optional<Salary> getSalaryById(String workerID) {
        String sql = "SELECT * FROM Salary WHERE WorkerID = ?";
        return jdbcTemplate.query(sql, new Object[]{workerID}, (rs, rowNum) -> mapRowToSalary(rs)).stream().findFirst();
    }

    // Method to create a new Salary record
    public int createSalary(Salary salary) {
        String sql = "INSERT INTO Salary (WorkerID, Amount) VALUES (?, ?)";
        return jdbcTemplate.update(sql, salary.getWorkerID(), salary.getAmount());
    }

    // Method to update an existing Salary record
    public int updateSalary(String workerID, Salary salary) {
        String sql = "UPDATE Salary SET Amount = ? WHERE WorkerID = ?";
        return jdbcTemplate.update(sql, salary.getAmount(), workerID);
    }

    // Method to delete a Salary record
    public int deleteSalary(String workerID) {
        String sql = "DELETE FROM Salary WHERE WorkerID = ?";
        return jdbcTemplate.update(sql, workerID);
    }

    // Method to map a ResultSet row to a Salary object
    private Salary mapRowToSalary(ResultSet rs) throws SQLException {
        Salary salary = new Salary();
        salary.setWorkerID(rs.getString("WorkerID"));
        salary.setAmount(rs.getInt("Amount"));
        return salary;
    }
}
