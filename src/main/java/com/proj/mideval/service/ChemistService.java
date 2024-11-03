package com.proj.mideval.service;

import com.proj.mideval.model.Chemist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ChemistService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Method to get all chemists
    public List<Chemist> getAllChemists() {
        String sql = "SELECT * FROM Chemist";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToChemist(rs));
    }

    // Method to get a specific chemist by ID
    public Optional<Chemist> getChemistById(int chemistID) {
        String sql = "SELECT * FROM Chemist WHERE ChemistID = ?";
        return jdbcTemplate.query(sql, new Object[]{chemistID}, (rs, rowNum) -> mapRowToChemist(rs)).stream().findFirst();
    }

    // Method to get a specific chemist by email
    public Optional<Chemist> getChemistByEmail(String email) {
        String sql = "SELECT * FROM Chemist WHERE Email = ?";
        return jdbcTemplate.query(sql, new Object[]{email}, (rs, rowNum) -> mapRowToChemist(rs)).stream().findFirst();
    }

    // Method to create a new chemist record with password encoding
    public int createChemist(Chemist chemist) {
        String sql = "INSERT INTO Chemist ( FirstName, LastName, Dob, Gender, Email, Phone, Password) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        String encodedPassword = passwordEncoder.encode(chemist.getPassword());
        return jdbcTemplate.update(sql, chemist.getFirstName(), chemist.getLastName(),
                chemist.getDob(), chemist.getGender(), chemist.getEmail(), chemist.getPhone(), encodedPassword);
    }

    // Method to update an existing chemist record with password encoding
    public int updateChemist(int chemistID, Chemist chemist) {
        String sql = "UPDATE Chemist SET FirstName = ?, LastName = ?, Dob = ?, Gender = ?, Email = ?, Phone = ?, Password = ? WHERE ChemistID = ?";
        String encodedPassword = passwordEncoder.encode(chemist.getPassword());
        return jdbcTemplate.update(sql, chemist.getFirstName(), chemist.getLastName(), chemist.getDob(),
                chemist.getGender(), chemist.getEmail(), chemist.getPhone(), encodedPassword, chemistID);
    }

    public Integer getChemistIDByEmail(String email) {
        String sql = "SELECT ChemistID FROM Chemist WHERE Email = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, email);
    }

    // Method to delete a chemist record
    public int deleteChemist(int chemistID) {
        String sql = "DELETE FROM Chemist WHERE ChemistID = ?";
        return jdbcTemplate.update(sql, chemistID);
    }

    // Method to authenticate a chemist by email and password
    public Optional<Chemist> authenticateChemist(String email, String password) {
        Optional<Chemist> chemist = getChemistByEmail(email);
        return chemist.filter(c -> passwordEncoder.matches(password, c.getPassword()));
    }

    // Method to map a ResultSet row to a Chemist object
    private Chemist mapRowToChemist(ResultSet rs) throws SQLException {
        Chemist chemist = new Chemist();
        chemist.setChemistID(rs.getInt("ChemistID"));
        chemist.setFirstName(rs.getString("FirstName"));
        chemist.setLastName(rs.getString("LastName"));
        chemist.setDob(rs.getDate("Dob"));
        chemist.setGender(rs.getString("Gender"));
        chemist.setEmail(rs.getString("Email"));
        chemist.setPhone(rs.getString("Phone"));
        chemist.setPassword(rs.getString("Password"));
        return chemist;
    }
}
