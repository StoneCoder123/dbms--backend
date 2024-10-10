package com.proj.mideval.service;

import com.proj.mideval.model.ApprovesExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApprovesExpenseService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ApprovesExpense> getAllApprovedExpenses() {
        String sql = "SELECT * FROM Approves_Expense";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToApprovesExpense(rs));
    }

    public Optional<ApprovesExpense> getApprovedExpenseById(int adminID, int deptID) {
        String sql = "SELECT * FROM Approves_Expense WHERE AdminID = ? AND DeptID = ?";
        return jdbcTemplate.query(sql, new Object[]{adminID, deptID}, (rs, rowNum) -> mapRowToApprovesExpense(rs)).stream().findFirst();
    }

    public int createApprovedExpense(ApprovesExpense approvesExpense) {
        String sql = "INSERT INTO Approves_Expense (AdminID, DeptID, Type, Amount) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, approvesExpense.getAdminID(), approvesExpense.getDeptID(), approvesExpense.getType(), approvesExpense.getAmount());
    }

    public int updateApprovedExpense(int adminID, int deptID, ApprovesExpense approvesExpense) {
        String sql = "UPDATE Approves_Expense SET Type = ?, Amount = ? WHERE AdminID = ? AND DeptID = ?";
        return jdbcTemplate.update(sql, approvesExpense.getType(), approvesExpense.getAmount(), adminID, deptID);
    }

    public int deleteApprovedExpense(int adminID, int deptID) {
        String sql = "DELETE FROM Approves_Expense WHERE AdminID = ? AND DeptID = ?";
        return jdbcTemplate.update(sql, adminID, deptID);
    }

    private ApprovesExpense mapRowToApprovesExpense(ResultSet rs) throws SQLException {
        ApprovesExpense approvesExpense = new ApprovesExpense();
        approvesExpense.setAdminID(rs.getInt("AdminID"));
        approvesExpense.setDeptID(rs.getInt("DeptID"));
        approvesExpense.setType(rs.getString("Type"));
        approvesExpense.setAmount(rs.getInt("Amount"));
        return approvesExpense;
    }
}
