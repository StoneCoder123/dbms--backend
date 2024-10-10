package com.proj.mideval.service;

import com.proj.mideval.model.DoctorUses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorUsesService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to get all doctor uses
    public List<DoctorUses> getAllDoctorUses() {
        String sql = "SELECT * FROM DoctorUses";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToDoctorUses(rs));
    }

    // Method to get a specific DoctorUses entry by doctorID and machineID
    public Optional<DoctorUses> getDoctorUsesById(int doctorID, int machineID) {
        String sql = "SELECT * FROM DoctorUses WHERE DoctorID = ? AND MachineID = ?";
        return jdbcTemplate.query(sql, new Object[]{doctorID, machineID}, (rs, rowNum) -> mapRowToDoctorUses(rs)).stream().findFirst();
    }

    // Method to create a new DoctorUses record
    public int createDoctorUses(DoctorUses doctorUses) {
        String sql = "INSERT INTO DoctorUses (DoctorID, MachineID, AccessLevel, CostPerHour) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, doctorUses.getDoctorID(), doctorUses.getMachineID(), doctorUses.getAccessLevel(), doctorUses.getCostPerHour());
    }

    // Method to update an existing DoctorUses record
    public int updateDoctorUses(int doctorID, int machineID, DoctorUses doctorUses) {
        String sql = "UPDATE DoctorUses SET AccessLevel = ?, CostPerHour = ? WHERE DoctorID = ? AND MachineID = ?";
        return jdbcTemplate.update(sql, doctorUses.getAccessLevel(), doctorUses.getCostPerHour(), doctorID, machineID);
    }

    // Method to delete a DoctorUses record
    public int deleteDoctorUses(int doctorID, int machineID) {
        String sql = "DELETE FROM DoctorUses WHERE DoctorID = ? AND MachineID = ?";
        return jdbcTemplate.update(sql, doctorID, machineID);
    }

    // Method to map a ResultSet row to a DoctorUses object
    private DoctorUses mapRowToDoctorUses(ResultSet rs) throws SQLException {
        DoctorUses doctorUses = new DoctorUses();
        doctorUses.setDoctorID(rs.getInt("DoctorID"));
        doctorUses.setMachineID(rs.getInt("MachineID"));
        doctorUses.setAccessLevel(rs.getInt("AccessLevel"));
        doctorUses.setCostPerHour(rs.getInt("CostPerHour"));
        return doctorUses;
    }
}
