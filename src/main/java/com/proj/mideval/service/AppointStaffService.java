package com.proj.mideval.service;

import com.proj.mideval.model.AppointStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointStaffService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<AppointStaff> getAllAppointStaffs() {
        String sql = "SELECT * FROM Appoint_Staff";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToAppointStaff(rs));
    }

    public Optional<AppointStaff> getAppointStaffById(int adminID, int staffID) {
        String sql = "SELECT * FROM Appoint_Staff WHERE AdminID = ? AND StaffID = ?";
        return jdbcTemplate.query(sql, new Object[]{adminID, staffID}, (rs, rowNum) -> mapRowToAppointStaff(rs)).stream().findFirst();
    }

    public int createAppointStaff(AppointStaff appointStaff) {
        String sql = "INSERT INTO Appoint_Staff (AdminID, StaffID, Remarks, Strengths, Weakness) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, appointStaff.getAdminID(), appointStaff.getStaffID(), appointStaff.getRemarks(), appointStaff.getStrengths(), appointStaff.getWeakness());
    }

    public int updateAppointStaff(int adminID, int staffID, AppointStaff appointStaff) {
        String sql = "UPDATE Appoint_Staff SET Remarks = ?, Strengths = ?, Weakness = ? WHERE AdminID = ? AND StaffID = ?";
        return jdbcTemplate.update(sql, appointStaff.getRemarks(), appointStaff.getStrengths(), appointStaff.getWeakness(), adminID, staffID);
    }

    public int deleteAppointStaff(int adminID, int staffID) {
        String sql = "DELETE FROM Appoint_Staff WHERE AdminID = ? AND StaffID = ?";
        return jdbcTemplate.update(sql, adminID, staffID);
    }

    private AppointStaff mapRowToAppointStaff(ResultSet rs) throws SQLException {
        AppointStaff appointStaff = new AppointStaff();
        appointStaff.setAdminID(rs.getInt("AdminID"));
        appointStaff.setStaffID(rs.getInt("StaffID"));
        appointStaff.setRemarks(rs.getString("Remarks"));
        appointStaff.setStrengths(rs.getString("Strengths"));
        appointStaff.setWeakness(rs.getString("Weakness"));
        return appointStaff;
    }
}
