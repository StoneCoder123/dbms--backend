package com.proj.mideval.service;

import com.proj.mideval.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PatientService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Integer getPatientIdByEmail(String email) {
        String sql = "SELECT PatientID FROM Patient WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, email);
    }

    public List<Patient> getAllPatients() {
        String sql = "SELECT * FROM Patient";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToPatient(rs));
    }

    public Optional<Patient> getPatientById(int patientID) {
        String sql = "SELECT * FROM Patient WHERE PatientID = ?";
        return jdbcTemplate.query(sql, new Object[]{patientID}, (rs, rowNum) -> mapRowToPatient(rs)).stream().findFirst();
    }

    public Optional<Patient> getPatientByEmail(String email) {
        String sql = "SELECT * FROM Patient WHERE email = ?";
        return jdbcTemplate.query(sql, new Object[]{email}, (rs, rowNum) -> mapRowToPatient(rs)).stream().findFirst();
    }

    public int createPatient(Patient patient) {
        String sql = "INSERT INTO Patient (FirstName, LastName, Address, NTK, Email, Gender, History, Dob, Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String hashedPassword = passwordEncoder.encode(patient.getPassword());
        return jdbcTemplate.update(sql, patient.getFirstName(), patient.getLastName(), patient.getAddress(), patient.getNTK(), patient.getEmail(), patient.getGender(), patient.getHistory(), patient.getDob(), hashedPassword);
    }

    public int updatePatient(int patientID, Patient patient) {
        String sql = "UPDATE Patient SET FirstName = ?, LastName = ?, Address = ?, NTK = ?, Email = ?, Gender = ?, History = ?, Dob = ? WHERE PatientID = ?";
        return jdbcTemplate.update(sql, patient.getFirstName(), patient.getLastName(), patient.getAddress(), patient.getNTK(), patient.getEmail(), patient.getGender(), patient.getHistory(), patient.getDob(), patientID);
    }

    public Optional<Patient> authenticatePatient(String email, String password) {
        Optional<Patient> patientOpt = getPatientByEmail(email);
        if (patientOpt.isPresent()) {
            Patient patient = patientOpt.get();
            if (passwordEncoder.matches(password, patient.getPassword())) {
                return Optional.of(patient);
            }
        }
        return Optional.empty();
    }

    public int deletePatient(int patientID) {
        String sql = "DELETE FROM Patient WHERE PatientID = ?";
        return jdbcTemplate.update(sql, patientID);
    }

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
        patient.setPassword(rs.getString("Password"));
        return patient;
    }
}
