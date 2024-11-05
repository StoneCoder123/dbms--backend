package com.proj.mideval.controller;

import com.proj.mideval.model.PharmacyRequests;
import com.proj.mideval.service.PharmacyRequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmacy")
public class PharmacyRequestsController {

    @Autowired
    private PharmacyRequestsService pharmacyRequestsService;


    @GetMapping
    public List<PharmacyRequests> getAllRequests() {return pharmacyRequestsService.getAllPharmacyRequests(); }

    @GetMapping("/{patientID}")
    public List<PharmacyRequests> getPharmacyRequestsByPatientID(@PathVariable int patientID) {
        return pharmacyRequestsService.getPharmacyRequestsByPatientID(patientID);
    }

    @PostMapping
    public String addPharmacyRequest(@RequestBody PharmacyRequests pharmacyRequest) {
        int result = pharmacyRequestsService.addPharmacyRequest(pharmacyRequest);
        if (result > 0) {
            return "Pharmacy request added successfully!";
        } else {
            return "Failed to add pharmacy request.";
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> denyRequest(@PathVariable int id) {
        pharmacyRequestsService.deletePharmacyRequest(id);
        return ResponseEntity.ok().build();
    }
}
