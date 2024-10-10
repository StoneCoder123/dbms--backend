package com.proj.mideval.controller;

import com.proj.mideval.model.Prescription;
import com.proj.mideval.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }

    @GetMapping("/{prescriptionID}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable int prescriptionID) {
        Optional<Prescription> prescription = prescriptionService.getPrescriptionById(prescriptionID);
        return prescription.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Prescription> createPrescription(@RequestBody Prescription prescription) {
        int result = prescriptionService.createPrescription(prescription);
        if (result > 0) {
            return ResponseEntity.status(201).body(prescription);  // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{prescriptionID}")
    public ResponseEntity<Prescription> updatePrescription(@PathVariable int prescriptionID, @RequestBody Prescription prescription) {
        int result = prescriptionService.updatePrescription(prescriptionID, prescription);
        if (result > 0) {
            return ResponseEntity.ok(prescription);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{prescriptionID}")
    public ResponseEntity<Void> deletePrescription(@PathVariable int prescriptionID) {
        int result = prescriptionService.deletePrescription(prescriptionID);
        if (result > 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
