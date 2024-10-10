package com.proj.mideval.service;

import com.proj.mideval.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to get all doctors
    public List<Doctor> getAllDoctors() {
        String sql = "SELECT * FROM Doctor";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToDoctor(rs));
    }

    // Method to get a specific doctor by ID
    public Optional<Doctor> getDoctorById(int doctorID) {
        String sql = "SELECT * FROM Doctor WHERE DoctorID = ?";
        return jdbcTemplate.query(sql, new Object[]{doctorID}, (rs, rowNum) -> mapRowToDoctor(rs)).stream().findFirst();
    }

    // Method to create a new doctor record
    public int createDoctor(Doctor doctor) {
        String sql = "INSERT INTO Doctor (DoctorID, FirstName, LastName, Dob, Education, Gender, Phone, Mail, Post, Department, Specialization) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,doctor.getDoctorID() ,doctor.getFirstName(), doctor.getLastName(), doctor.getDob(), doctor.getEducation(), doctor.getGender(), doctor.getPhone(), doctor.getMail(), doctor.getPost(), doctor.getDepartment(), doctor.getSpecialization());
    }

    // Method to update an existing doctor record
    public int updateDoctor(int doctorID, Doctor doctor) {
        String sql = "UPDATE Doctor SET FirstName = ?, LastName = ?, Dob = ?, Education = ?, Gender = ?, Phone = ?, Mail = ?, Post = ?, Department = ?, Specialization = ? WHERE DoctorID = ?";
        return jdbcTemplate.update(sql, doctor.getFirstName(), doctor.getLastName(), doctor.getDob(), doctor.getEducation(), doctor.getGender(), doctor.getPhone(), doctor.getMail(), doctor.getPost(), doctor.getDepartment(), doctor.getSpecialization(), doctorID);
    }

    // Method to delete a doctor record
    public int deleteDoctor(int doctorID) {
        String sql = "DELETE FROM Doctor WHERE DoctorID = ?";
        return jdbcTemplate.update(sql, doctorID);
    }

    // Method to map a ResultSet row to a Doctor object
    private Doctor mapRowToDoctor(ResultSet rs) throws SQLException {
        Doctor doctor = new Doctor();
        doctor.setDoctorID(rs.getInt("DoctorID"));
        doctor.setFirstName(rs.getString("FirstName"));
        doctor.setLastName(rs.getString("LastName"));
        doctor.setDob(rs.getDate("Dob"));
        doctor.setEducation(rs.getString("Education"));
        doctor.setGender(rs.getString("Gender")); // Assuming gender is stored as a single character
        doctor.setPhone(rs.getString("Phone"));
        doctor.setMail(rs.getString("Mail"));
        doctor.setPost(rs.getString("Post"));
        doctor.setDepartment(rs.getString("Department"));
        doctor.setSpecialization(rs.getString("Specialization"));
        return doctor;
    }
}
