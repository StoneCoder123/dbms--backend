package com.proj.mideval.service;

import com.proj.mideval.model.AppointDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointDoctorService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<AppointDoctor> getAllAppointDoctors() {
        String sql = "SELECT * FROM Appoint_Doctor";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToAppointDoctor(rs));
    }

    public Optional<AppointDoctor> getAppointDoctorById(int adminID, int doctorID) {
        String sql = "SELECT * FROM Appoint_Doctor WHERE AdminID = ? AND DoctorID = ?";
        return jdbcTemplate.query(sql, new Object[]{adminID, doctorID}, (rs, rowNum) -> mapRowToAppointDoctor(rs)).stream().findFirst();
    }

    public int createAppointDoctor(AppointDoctor appointDoctor) {
        String sql = "INSERT INTO Appoint_Doctor (AdminID, DoctorID, Post, Department, Salary) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, appointDoctor.getAdminID(), appointDoctor.getDoctorID(), appointDoctor.getPost(), appointDoctor.getDepartment(), appointDoctor.getSalary());
    }

    public int updateAppointDoctor(int adminID, int doctorID, AppointDoctor appointDoctor) {
        String sql = "UPDATE Appoint_Doctor SET Post = ?, Department = ?, Salary = ? WHERE AdminID = ? AND DoctorID = ?";
        return jdbcTemplate.update(sql, appointDoctor.getPost(), appointDoctor.getDepartment(), appointDoctor.getSalary(), adminID, doctorID);
    }

    public int deleteAppointDoctor(int adminID, int doctorID) {
        String sql = "DELETE FROM Appoint_Doctor WHERE AdminID = ? AND DoctorID = ?";
        return jdbcTemplate.update(sql, adminID, doctorID);
    }

    private AppointDoctor mapRowToAppointDoctor(ResultSet rs) throws SQLException {
        AppointDoctor appointDoctor = new AppointDoctor();
        appointDoctor.setAdminID(rs.getInt("AdminID"));
        appointDoctor.setDoctorID(rs.getInt("DoctorID"));
        appointDoctor.setPost(rs.getString("Post"));
        appointDoctor.setDepartment(rs.getString("Department"));
        appointDoctor.setSalary(rs.getInt("Salary"));
        return appointDoctor;
    }
}
