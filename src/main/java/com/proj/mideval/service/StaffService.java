package com.proj.mideval.service;

import com.proj.mideval.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Retrieve all Staff records
    public List<Staff> getAllStaff() {
        String sql = "SELECT * FROM Staff";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToStaff(rs));
    }

    // Retrieve a Staff record by ID
    public Optional<Staff> getStaffById(int staffID) {
        String sql = "SELECT * FROM Staff WHERE staffID = ?";
        return jdbcTemplate.query(sql, new Object[]{staffID}, (rs, rowNum) -> mapRowToStaff(rs)).stream().findFirst();
    }

    // Create a new Staff record
    public int createStaff(Staff staff) {
        String sql = "INSERT INTO Staff (firstName, lastName, dob, education, gender, post, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, staff.getFirstName(), staff.getLastName(), staff.getDob(), staff.getEducation(), staff.getGender(), staff.getPost(), staff.getEmail(), staff.getPhone());
    }

    // Update an existing Staff record
    public int updateStaff(int staffID, Staff staff) {
        String sql = "UPDATE Staff SET firstName = ?, lastName = ?, dob = ?, education = ?, gender = ?, post = ?, email = ?, phone = ? WHERE staffID = ?";
        return jdbcTemplate.update(sql, staff.getFirstName(), staff.getLastName(), staff.getDob(), staff.getEducation(), staff.getGender(), staff.getPost(), staff.getEmail(), staff.getPhone(), staffID);
    }

    // Delete a Staff record by ID
    public int deleteStaff(int staffID) {
        String sql = "DELETE FROM Staff WHERE staffID = ?";
        return jdbcTemplate.update(sql, staffID);
    }

    // Map a ResultSet row to a Staff object
    private Staff mapRowToStaff(ResultSet rs) throws SQLException {
        Staff staff = new Staff();
        staff.setStaffID(rs.getInt("staffID"));
        staff.setFirstName(rs.getString("firstName"));
        staff.setLastName(rs.getString("lastName"));
        staff.setDob(rs.getDate("dob"));
        staff.setEducation(rs.getString("education"));
        staff.setGender(rs.getString("gender"));
        staff.setPost(rs.getString("post"));
        staff.setEmail(rs.getString("email"));
        staff.setPhone(rs.getString("phone"));
        return staff;
    }
}
