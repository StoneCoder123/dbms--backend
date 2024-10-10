package com.proj.mideval.service;

import com.proj.mideval.model.ExpenditureDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenditureDepartmentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to get all expenditure departments
    public List<ExpenditureDepartment> getAllExpenditureDepartments() {
        String sql = "SELECT * FROM ExpenditureDepartment";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToExpenditureDepartment(rs));
    }

    // Method to get a specific ExpenditureDepartment entry by deptID
    public Optional<ExpenditureDepartment> getExpenditureDepartmentById(int deptID) {
        String sql = "SELECT * FROM ExpenditureDepartment WHERE DeptID = ?";
        return jdbcTemplate.query(sql, new Object[]{deptID}, (rs, rowNum) -> mapRowToExpenditureDepartment(rs)).stream().findFirst();
    }

    // Method to create a new ExpenditureDepartment record
    public int createExpenditureDepartment(ExpenditureDepartment expenditureDepartment) {
        String sql = "INSERT INTO ExpenditureDepartment (DeptID, Name, Phone, Email) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, expenditureDepartment.getDeptID(), expenditureDepartment.getName(), expenditureDepartment.getPhone(), expenditureDepartment.getEmail());
    }

    // Method to update an existing ExpenditureDepartment record
    public int updateExpenditureDepartment(int deptID, ExpenditureDepartment expenditureDepartment) {
        String sql = "UPDATE ExpenditureDepartment SET Name = ?, Phone = ?, Email = ? WHERE DeptID = ?";
        return jdbcTemplate.update(sql, expenditureDepartment.getName(), expenditureDepartment.getPhone(), expenditureDepartment.getEmail(), deptID);
    }

    // Method to delete an ExpenditureDepartment record
    public int deleteExpenditureDepartment(int deptID) {
        String sql = "DELETE FROM ExpenditureDepartment WHERE DeptID = ?";
        return jdbcTemplate.update(sql, deptID);
    }

    // Method to map a ResultSet row to an ExpenditureDepartment object
    private ExpenditureDepartment mapRowToExpenditureDepartment(ResultSet rs) throws SQLException {
        ExpenditureDepartment expenditureDepartment = new ExpenditureDepartment();
        expenditureDepartment.setDeptID(rs.getInt("DeptID"));
        expenditureDepartment.setName(rs.getString("Name"));
        expenditureDepartment.setPhone(rs.getString("Phone"));
        expenditureDepartment.setEmail(rs.getString("Email"));
        return expenditureDepartment;
    }
}
