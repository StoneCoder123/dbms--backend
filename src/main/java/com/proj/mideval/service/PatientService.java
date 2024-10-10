package com.proj.mideval.service;

import com.proj.mideval.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to get all Patient records
    public List<Patient> getAllPatients() {
        String sql = "SELECT * FROM Patient";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToPatient(rs));
    }

    // Method to get a specific Patient entry by patientID
    public Optional<Patient> getPatientById(int patientID) {
        String sql = "SELECT * FROM Patient WHERE PatientID = ?";
        return jdbcTemplate.query(sql, new Object[]{patientID}, (rs, rowNum) -> mapRowToPatient(rs)).stream().findFirst();
    }

    // Method to create a new Patient record
    public int createPatient(Patient patient) {
        String sql = "INSERT INTO Patient (PatientID, FirstName, LastName, Address, NTK, Email, Gender, History, Dob) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, patient.getPatientID(), patient.getFirstName(), patient.getLastName(), patient.getAddress(), patient.getNTK(), patient.getEmail(), patient.getGender(), patient.getHistory(), patient.getDob());
    }

    // Method to update an existing Patient record
    public int updatePatient(int patientID, Patient patient) {
        String sql = "UPDATE Patient SET FirstName = ?, LastName = ?, Address = ?, NTK = ?, Email = ?, Gender = ?, History = ?, Dob = ? WHERE PatientID = ?";
        return jdbcTemplate.update(sql, patient.getFirstName(), patient.getLastName(), patient.getAddress(), patient.getNTK(), patient.getEmail(), patient.getGender(), patient.getHistory(), patient.getDob(), patientID);
    }

    // Method to delete a Patient record
    public int deletePatient(int patientID) {
        String sql = "DELETE FROM Patient WHERE PatientID = ?";
        return jdbcTemplate.update(sql, patientID);
    }

    // Method to map a ResultSet row to a Patient object
    private Patient mapRowToPatient(ResultSet rs) throws SQLException {
        Patient patient = new Patient();
        patient.setPatientID(rs.getInt("PatientID"));
        patient.setFirstName(rs.getString("FirstName"));
        patient.setLastName(rs.getString("LastName"));
        patient.setAddress(rs.getString("Address"));
        patient.setNTK(rs.getString("NTK"));
        patient.setEmail(rs.getString("Email"));
        patient.setGender(rs.getString("Gender"));
        patient.setHistory(rs.getString("History"));
        patient.setDob(rs.getDate("Dob"));
        return patient;
    }
}
