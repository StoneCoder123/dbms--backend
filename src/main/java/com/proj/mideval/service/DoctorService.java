package com.proj.mideval.service;

import com.proj.mideval.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Doctor> getAllDoctors() {
        String sql = "SELECT * FROM Doctor";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToDoctor(rs));
    }

    public Optional<Doctor> getDoctorById(int doctorID) {
        String sql = "SELECT * FROM Doctor WHERE DoctorID = ?";
        return jdbcTemplate.query(sql, new Object[]{doctorID}, (rs, rowNum) -> mapRowToDoctor(rs)).stream().findFirst();
    }

    public Optional<Doctor> getDoctorByEmail(String email) {
        String sql = "SELECT * FROM Doctor WHERE Email = ?";
        return jdbcTemplate.query(sql, new Object[]{email}, (rs, rowNum) -> mapRowToDoctor(rs)).stream().findFirst();
    }

    public int createDoctor(Doctor doctor) {
        String sql = "INSERT INTO Doctor (DoctorID, FirstName, LastName, Dob, Education, Gender, Phone, Email, Post, Department, Specialization, Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String encodedPassword = passwordEncoder.encode(doctor.getPassword()); // Encode password
        return jdbcTemplate.update(sql, doctor.getDoctorID(), doctor.getFirstName(), doctor.getLastName(), doctor.getDob(), doctor.getEducation(), doctor.getGender(), doctor.getPhone(), doctor.getEmail(), doctor.getPost(), doctor.getDepartment(), doctor.getSpecialization(), encodedPassword);
    }

    public int updateDoctor(int doctorID, Doctor doctor) {
        String sql = "UPDATE Doctor SET FirstName = ?, LastName = ?, Dob = ?, Education = ?, Gender = ?, Phone = ?, Email = ?, Post = ?, Department = ?, Specialization = ?, Password = ? WHERE DoctorID = ?";
        String encodedPassword = passwordEncoder.encode(doctor.getPassword());
        return jdbcTemplate.update(sql, doctor.getFirstName(), doctor.getLastName(), doctor.getDob(), doctor.getEducation(), doctor.getGender(), doctor.getPhone(), doctor.getEmail(), doctor.getPost(), doctor.getDepartment(), doctor.getSpecialization(), encodedPassword, doctorID);
    }

    public int deleteDoctor(int doctorID) {
        String sql = "DELETE FROM Doctor WHERE DoctorID = ?";
        return jdbcTemplate.update(sql, doctorID);
    }

    public Optional<Doctor> authenticateDoctor(String email, String password) {
        Optional<Doctor> doctor = getDoctorByEmail(email);
        return doctor.filter(d -> passwordEncoder.matches(password, d.getPassword()));
    }


    private Doctor mapRowToDoctor(ResultSet rs) throws SQLException {
        Doctor doctor = new Doctor();
        doctor.setDoctorID(rs.getInt("DoctorID"));
        doctor.setFirstName(rs.getString("FirstName"));
        doctor.setLastName(rs.getString("LastName"));
        doctor.setDob(rs.getDate("Dob"));
        doctor.setEducation(rs.getString("Education"));
        doctor.setGender(rs.getString("Gender"));
        doctor.setPhone(rs.getString("Phone"));
        doctor.setEmail(rs.getString("Email"));
        doctor.setPost(rs.getString("Post"));
        doctor.setDepartment(rs.getString("Department"));
        doctor.setSpecialization(rs.getString("Specialization"));
        doctor.setPassword(rs.getString("Password")); // Retrieve password
        return doctor;
    }
}
