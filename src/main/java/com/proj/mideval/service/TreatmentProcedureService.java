package com.proj.mideval.service;

import com.proj.mideval.model.TreatmentProcedure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class TreatmentProcedureService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Retrieve all TreatmentProcedure records
    public List<TreatmentProcedure> getAllTreatmentProcedures() {
        String sql = "SELECT * FROM TreatmentProcedure";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToTreatmentProcedure(rs));
    }

    // Retrieve a TreatmentProcedure record by doctorID and patientID
    public Optional<TreatmentProcedure> getTreatmentProcedureByDoctorIdAndPatientId(int doctorID, int patientID) {
        String sql = "SELECT * FROM TreatmentProcedure WHERE doctorID = ? AND patientID = ?";
        return jdbcTemplate.query(sql, new Object[]{doctorID, patientID}, (rs, rowNum) -> mapRowToTreatmentProcedure(rs)).stream().findFirst();
    }

    // Create a new TreatmentProcedure record
    public int createTreatmentProcedure(TreatmentProcedure treatmentProcedure) {
        String sql = "INSERT INTO TreatmentProcedure (doctorID, patientID, critical) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, treatmentProcedure.getDoctorID(), treatmentProcedure.getPatientID(), treatmentProcedure.getCritical());
    }

    // Delete a TreatmentProcedure record by doctorID and patientID
    public int deleteTreatmentProcedure(int doctorID, int patientID) {
        String sql = "DELETE FROM TreatmentProcedure WHERE doctorID = ? AND patientID = ?";
        return jdbcTemplate.update(sql, doctorID, patientID);
    }

    // Map a ResultSet row to a TreatmentProcedure object
    private TreatmentProcedure mapRowToTreatmentProcedure(ResultSet rs) throws SQLException {
        TreatmentProcedure treatmentProcedure = new TreatmentProcedure();
        treatmentProcedure.setDoctorID(rs.getInt("doctorID"));
        treatmentProcedure.setPatientID(rs.getInt("patientID"));
        treatmentProcedure.setCritical(rs.getInt("critical"));
        return treatmentProcedure;
    }
}
