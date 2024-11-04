package com.proj.mideval.service;

import com.proj.mideval.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public List<Bill> getBillByStatus1() {
        String sql = "SELECT * FROM Bill WHERE Status = 1";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToBill(rs));

    }

    // 1) Create a new bill
    public int createBill(int patientID, int totalCost, String type) {
        String sql = "INSERT INTO Bill (PatientID, TotalCost, Status, Type) VALUES (?, ?, 0, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, patientID);
            ps.setInt(2, totalCost);
            ps.setString(3, type);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue(); // Return the generated bill ID
    }


    // 2) Delete a bill
    public int deleteBill(int billID) {
        String sql = "DELETE FROM Bill WHERE BillID = ?";
        return jdbcTemplate.update(sql, billID);
    }

    // 3) Update the status of a bill
    public int updateBillStatus(int billID, int status) {
        System.out.println("Updating status in database");
        String sql = "UPDATE Bill SET Status = ? WHERE BillID = ?";
        return jdbcTemplate.update(sql, status, billID);
    }

    // 4) Update mapRowToBill to handle new attributes
    private Bill mapRowToBill(ResultSet rs) throws SQLException {
        Bill bill = new Bill();
        bill.setBillID(rs.getInt("BillID"));
        bill.setPatientID(rs.getInt("PatientID"));
        bill.setTotalCost(rs.getInt("TotalCost"));
        bill.setStatus(rs.getInt("Status")); // New field
        bill.setType(rs.getString("Type")); // New field
        return bill;
    }

    public List<Bill> getBillByPatientID(int patientID) {
        String sql = "SELECT * FROM Bill WHERE PatientID = ?";
        return jdbcTemplate.query(sql, new Object[]{patientID}, (rs, rowNum) -> mapRowToBill(rs));
    }

    // get the unpaid bills of a patient
    public List<Bill> getBillByPatientID_unpaid_ones(int patientID) {
        String sql = "SELECT * FROM Bill WHERE PatientID = ? AND STATUS=0";
        return jdbcTemplate.query(sql, new Object[]{patientID}, (rs, rowNum) -> mapRowToBill(rs));
    }
}
