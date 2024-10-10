package com.proj.mideval.service;

import com.proj.mideval.model.BillingDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class BillingDepartmentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to get all billing departments
    public List<BillingDepartment> getAllBillingDepartments() {
        String sql = "SELECT * FROM Billing_Department";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToBillingDepartment(rs));
    }

    // Method to get a billing department by ID
    public Optional<BillingDepartment> getBillingDepartmentById(int deptID) {
        String sql = "SELECT * FROM Billing_Department WHERE DeptID = ?";
        return jdbcTemplate.query(sql, new Object[]{deptID}, (rs, rowNum) -> mapRowToBillingDepartment(rs)).stream().findFirst();
    }

    // Method to create a new billing department
    public int createBillingDepartment(BillingDepartment billingDepartment) {
        String sql = "INSERT INTO Billing_Department (DeptID, Name, Phone, Email, BillID) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, billingDepartment.getDeptID(), billingDepartment.getName(),
                billingDepartment.getPhone(), billingDepartment.getEmail(), billingDepartment.getBillID());
    }

    // Method to update an existing billing department
    public int updateBillingDepartment(int deptID, BillingDepartment billingDepartment) {
        String sql = "UPDATE Billing_Department SET Name = ?, Phone = ?, Email = ?, BillID = ? WHERE DeptID = ?";
        return jdbcTemplate.update(sql, billingDepartment.getName(), billingDepartment.getPhone(),
                billingDepartment.getEmail(), billingDepartment.getBillID(), deptID);
    }

    // Method to delete a billing department
    public int deleteBillingDepartment(int deptID) {
        String sql = "DELETE FROM Billing_Department WHERE DeptID = ?";
        return jdbcTemplate.update(sql, deptID);
    }

    // Method to map a ResultSet row to a BillingDepartment object
    private BillingDepartment mapRowToBillingDepartment(ResultSet rs) throws SQLException {
        BillingDepartment billingDepartment = new BillingDepartment();
        billingDepartment.setDeptID(rs.getInt("DeptID"));
        billingDepartment.setName(rs.getString("Name"));
        billingDepartment.setPhone(rs.getString("Phone"));
        billingDepartment.setEmail(rs.getString("Email"));
        billingDepartment.setBillID(rs.getInt("BillID"));
        return billingDepartment;
    }
}
