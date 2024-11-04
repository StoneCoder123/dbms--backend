package com.proj.mideval.controller;

import com.proj.mideval.model.MedicineRequests;
import com.proj.mideval.service.MedicineRequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicine-requests")
public class MedicineRequestsController {

    @Autowired
    private MedicineRequestsService medicineRequestsService;

    @GetMapping
    public List<MedicineRequests> getAllRequests() {
        return medicineRequestsService.getAllRequests();
    }

    @PostMapping
    public ResponseEntity<MedicineRequests> addRequest(@RequestBody MedicineRequests request) {
        int result = medicineRequestsService.addMedicineRequest(request);
        if (result > 0) {
            return ResponseEntity.status(201).body(request);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/{requestID}/accept")
    public ResponseEntity<Void> acceptRequest(@PathVariable int requestID) {
        if (medicineRequestsService.acceptRequest(requestID)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{requestID}/deny")
    public ResponseEntity<Void> denyRequest(@PathVariable int requestID) {
        if (medicineRequestsService.denyRequest(requestID)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
