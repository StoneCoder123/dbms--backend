package com.proj.mideval.service;

import com.proj.mideval.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final RowMapper<Admin> adminRowMapper = new RowMapper<Admin>() {
        @Override
        public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
            Admin admin = new Admin();
            admin.setAdminID(rs.getInt("AdminID"));
            admin.setDoctorID(rs.getInt("DoctorID"));
            admin.setAccessLevel(rs.getString("AccessLevel"));
            admin.setPassword(rs.getString("Password"));
            return admin;
        }
    };

    public List<Admin> getAllAdmins() {
        String sql = "SELECT * FROM Admin";
        return jdbcTemplate.query(sql, adminRowMapper);
    }

    public Optional<Admin> getAdminById(int id) {
        String sql = "SELECT * FROM Admin WHERE AdminID = ?";
        return jdbcTemplate.query(sql, adminRowMapper, id).stream().findFirst();
    }

    public Admin createAdmin(Admin admin) {
        String sql = "INSERT INTO Admin (AdminID, DoctorID, AccessLevel, Password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, admin.getAdminID(), admin.getDoctorID(), admin.getAccessLevel());
        return admin;
    }

    public Optional<Admin> updateAdmin(int id, Admin adminDetails) {
        String sql = "UPDATE Admin SET DoctorID = ?, AccessLevel = ? WHERE AdminID = ?";
        int result = jdbcTemplate.update(sql, adminDetails.getDoctorID(), adminDetails.getAccessLevel(), id);
        if (result > 0) {
            return Optional.of(adminDetails);
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteAdmin(int id) {
        String sql = "DELETE FROM Admin WHERE AdminID = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    public List<Admin> getAdminsByDoctorID(int doctorID) {
        String sql = "SELECT * FROM Admin WHERE DoctorID = ?";
        return jdbcTemplate.query(sql, adminRowMapper, doctorID);
    }

    public Optional<Admin> updateAccessLevel(int id, String accessLevel) {
        String sql = "UPDATE Admin SET AccessLevel = ? WHERE AdminID = ?";
        int result = jdbcTemplate.update(sql, accessLevel, id);
        if (result > 0) {
            return getAdminById(id);
        } else {
            return Optional.empty();
        }
    }
}
