package com.proj.mideval.service;

import com.proj.mideval.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Bill> getAllBills() {
        String sql = "SELECT * FROM Bill";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToBill(rs));
    }

    public Optional<Bill> getBillById(int billID) {
        String sql = "SELECT * FROM Bill WHERE BillID = ?";
        return jdbcTemplate.query(sql, new Object[]{billID}, (rs, rowNum) -> mapRowToBill(rs)).stream().findFirst();
    }

    public int createBill(Bill bill) {
        String sql = "INSERT INTO Bill (BillID, PatientID, TotalCost) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql,bill.getBillID() ,  bill.getPatientID(), bill.getTotalCost());
    }

    public int updateBill(int billID, Bill bill) {
        String sql = "UPDATE Bill SET PatientID = ?, TotalCost = ? WHERE BillID = ?";
        return jdbcTemplate.update(sql, bill.getPatientID(), bill.getTotalCost(), billID);
    }

    public int deleteBill(int billID) {
        String sql = "DELETE FROM Bill WHERE BillID = ?";
        return jdbcTemplate.update(sql, billID);
    }

    private Bill mapRowToBill(ResultSet rs) throws SQLException {
        Bill bill = new Bill();
        bill.setBillID(rs.getInt("BillID"));
        bill.setPatientID(rs.getInt("PatientID"));
        bill.setTotalCost(rs.getInt("TotalCost"));
        return bill;
    }
}
