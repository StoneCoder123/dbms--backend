package com.proj.mideval.service;

import com.proj.mideval.model.Bill;
import com.proj.mideval.model.MedicineRequests;
import com.proj.mideval.model.Medicines;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineRequestsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MedicinesService medicinesService;

    private static final Logger logger = LoggerFactory.getLogger(MedicineRequestsService.class);


    public int addMedicineRequest(MedicineRequests request) {
        String sql = "INSERT INTO MedicineRequests (MedicineID, MedicineName, Cost, Type, CompanyName, Amount) VALUES (?, ?, ?, ?, ?, ?)";
        int id = medicinesService.getMedicineIDByName(request.getMedicineName());
        return jdbcTemplate.update(sql, id , request.getMedicineName(), request.getCost(), request.getType(), request.getCompanyName(), request.getAmount());
    }

    public List<MedicineRequests> getAllRequests() {
        String sql = "SELECT * FROM MedicineRequests";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            MedicineRequests request = new MedicineRequests();
            request.setRequestID(rs.getInt("RequestID"));
            request.setMedicineID(rs.getInt("MedicineID"));
            request.setMedicineName(rs.getString("MedicineName"));
            request.setCost(rs.getInt("Cost"));
            request.setType(rs.getString("Type"));
            request.setCompanyName(rs.getString("CompanyName"));
            request.setAmount(rs.getInt("Amount"));
            return request;
        });
    }



    public boolean acceptRequest(int requestID) {
        String sql = "SELECT * FROM MedicineRequests WHERE RequestID = ?";
        MedicineRequests request = jdbcTemplate.queryForObject(sql, new Object[]{requestID}, (rs, rowNum) -> {
            MedicineRequests req = new MedicineRequests();
            req.setRequestID(rs.getInt("RequestID"));
            req.setMedicineID(rs.getInt("MedicineID"));
            req.setMedicineName(rs.getString("MedicineName"));
            req.setCost(rs.getInt("Cost"));
            req.setType(rs.getString("Type"));
            req.setCompanyName(rs.getString("CompanyName"));
            req.setAmount(rs.getInt("Amount"));
            return req;
        });

        int id = medicinesService.getMedicineIDByName(request.getMedicineName());
        logger.info("Attempting to add request with ID: {}", id);

        if (id != 0) {
            Optional<Medicines> optionalMedicine = medicinesService.getMedicinesById(id);
            if (optionalMedicine.isPresent()) {
                logger.info("medicine exists");
                Medicines existingMedicine = optionalMedicine.get();
                existingMedicine.setAmount(existingMedicine.getAmount() + request.getAmount());
                medicinesService.updateMedicines(id, existingMedicine);
            }
        } else {
            Medicines newMedicine = new Medicines();
            logger.info("new medicine");
            newMedicine.setMedicineName(request.getMedicineName());
            newMedicine.setCost(request.getCost());
            newMedicine.setType(request.getType());
            newMedicine.setCompanyName(request.getCompanyName());
            newMedicine.setAmount(request.getAmount());
            medicinesService.createMedicines(newMedicine);
        }

        jdbcTemplate.update("DELETE FROM MedicineRequests WHERE RequestID = ?", requestID);
        return true;
    }



    public boolean denyRequest(int requestID) {
        logger.info("Attempting to delete request with ID: {}", requestID);
        String sql = "DELETE FROM MedicineRequests WHERE requestID = ?";
        int rowsAffected = jdbcTemplate.update(sql, requestID);
        logger.info("Rows affected: {}", rowsAffected);
        return rowsAffected > 0;
    }

}
