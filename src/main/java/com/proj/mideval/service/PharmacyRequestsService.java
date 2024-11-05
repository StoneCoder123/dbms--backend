package com.proj.mideval.service;

import com.proj.mideval.model.PharmacyRequests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacyRequestsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(PharmacyRequestsService.class);

    // Method to add a new pharmacy request
    public int addPharmacyRequest(PharmacyRequests request) {
        String sql = "INSERT INTO PharmacyRequests (MedicineName, PatientID, Amount) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, request.getMedicineName(), request.getPatientID(), request.getAmount());
        logger.info("Added pharmacy request. Rows affected: {}", rowsAffected);
        return rowsAffected;
    }

    // Method to delete a pharmacy request by RequestID
    public boolean deletePharmacyRequest(int requestID) {
        String sql = "DELETE FROM PharmacyRequests WHERE RequestID = ?";
        int rowsAffected = jdbcTemplate.update(sql, requestID);
        logger.info("Deleted pharmacy request with ID: {}. Rows affected: {}", requestID, rowsAffected);
        return rowsAffected > 0;
    }

    // Method to get all pharmacy requests
    public List<PharmacyRequests> getAllPharmacyRequests() {
        String sql = "SELECT * FROM PharmacyRequests";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            PharmacyRequests request = new PharmacyRequests();
            request.setRequestID(rs.getInt("RequestID"));
            request.setMedicineName(rs.getString("MedicineName"));
            request.setPatientID(rs.getInt("PatientID"));
            request.setAmount(rs.getInt("Amount"));
            return request;
        });
    }

    public List<PharmacyRequests> getPharmacyRequestsByPatientID(int patientID) {
        String sql = "SELECT * FROM PharmacyRequests WHERE PatientID = ?";
        return jdbcTemplate.query(sql, new Object[]{patientID}, (rs, rowNum) -> {
            PharmacyRequests request = new PharmacyRequests();
            request.setRequestID(rs.getInt("RequestID"));
            request.setMedicineName(rs.getString("MedicineName"));
            request.setPatientID(rs.getInt("PatientID"));
            request.setAmount(rs.getInt("Amount"));
            return request;
        });
    }
}
