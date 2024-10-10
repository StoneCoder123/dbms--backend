package com.proj.mideval.service;

import com.proj.mideval.model.AppointChemist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointChemistService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<AppointChemist> getAllAppointChemists() {
        String sql = "SELECT * FROM Appoint_Chemist";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToAppointChemist(rs));
    }

    public Optional<AppointChemist> getAppointChemistById(int adminID, int chemistID) {
        String sql = "SELECT * FROM Appoint_Chemist WHERE AdminID = ? AND ChemistID = ?";
        return jdbcTemplate.query(sql, new Object[]{adminID, chemistID}, (rs, rowNum) -> mapRowToAppointChemist(rs)).stream().findFirst();
    }

    public int createAppointChemist(AppointChemist appointChemist) {
        String sql = "INSERT INTO Appoint_Chemist (AdminID, ChemistID, Remarks, Strengths, Weakness) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, appointChemist.getAdminID(), appointChemist.getChemistID(), appointChemist.getRemarks(), appointChemist.getStrengths(), appointChemist.getWeakness());
    }

    public int updateAppointChemist(int adminID, int chemistID, AppointChemist appointChemist) {
        String sql = "UPDATE Appoint_Chemist SET Remarks = ?, Strengths = ?, Weakness = ? WHERE AdminID = ? AND ChemistID = ?";
        return jdbcTemplate.update(sql, appointChemist.getRemarks(), appointChemist.getStrengths(), appointChemist.getWeakness(), adminID, chemistID);
    }

    public int deleteAppointChemist(int adminID, int chemistID) {
        String sql = "DELETE FROM Appoint_Chemist WHERE AdminID = ? AND ChemistID = ?";
        return jdbcTemplate.update(sql, adminID, chemistID);
    }

    private AppointChemist mapRowToAppointChemist(ResultSet rs) throws SQLException {
        AppointChemist appointChemist = new AppointChemist();
        appointChemist.setAdminID(rs.getInt("AdminID"));
        appointChemist.setChemistID(rs.getInt("ChemistID"));
        appointChemist.setRemarks(rs.getString("Remarks"));
        appointChemist.setStrengths(rs.getString("Strengths"));
        appointChemist.setWeakness(rs.getString("Weakness"));
        return appointChemist;
    }
}
