package com.proj.mideval.service;

import com.proj.mideval.model.Machinery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class MachineryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to get all Machinery records
    public List<Machinery> getAllMachinery() {
        String sql = "SELECT * FROM Machinery";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToMachinery(rs));
    }

    // Method to get a specific Machinery entry by machineID
    public Optional<Machinery> getMachineryById(int machineID) {
        String sql = "SELECT * FROM Machinery WHERE MachineID = ?";
        return jdbcTemplate.query(sql, new Object[]{machineID}, (rs, rowNum) -> mapRowToMachinery(rs)).stream().findFirst();
    }

    // Method to create a new Machinery record
    public int createMachinery(Machinery machinery) {
        String sql = "INSERT INTO Machinery (MachineID, Name, Cost) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, machinery.getMachineID(), machinery.getName(), machinery.getCost());
    }

    // Method to update an existing Machinery record
    public int updateMachinery(int machineID, Machinery machinery) {
        String sql = "UPDATE Machinery SET Name = ?, Cost = ? WHERE MachineID = ?";
        return jdbcTemplate.update(sql, machinery.getName(), machinery.getCost(), machineID);
    }

    // Method to delete a Machinery record
    public int deleteMachinery(int machineID) {
        String sql = "DELETE FROM Machinery WHERE MachineID = ?";
        return jdbcTemplate.update(sql, machineID);
    }

    // Method to map a ResultSet row to a Machinery object
    private Machinery mapRowToMachinery(ResultSet rs) throws SQLException {
        Machinery machinery = new Machinery();
        machinery.setMachineID(rs.getInt("MachineID"));
        machinery.setName(rs.getString("Name"));
        machinery.setCost(rs.getInt("Cost"));
        return machinery;
    }
}
