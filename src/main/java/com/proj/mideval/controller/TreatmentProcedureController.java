package com.proj.mideval.controller;

import com.proj.mideval.model.TreatmentProcedure;
import com.proj.mideval.service.TreatmentProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/treatmentProcedure")
public class TreatmentProcedureController {

    @Autowired
    private TreatmentProcedureService treatmentProcedureService;

    // Retrieve all TreatmentProcedure records
    @GetMapping
    public List<TreatmentProcedure> getAllTreatmentProcedures() {
        return treatmentProcedureService.getAllTreatmentProcedures();
    }

    // Retrieve a TreatmentProcedure record by doctorID and patientID
    @GetMapping("/{doctorID}/{patientID}")
    public ResponseEntity<TreatmentProcedure> getTreatmentProcedureByDoctorIdAndPatientId(@PathVariable int doctorID, @PathVariable int patientID) {
        Optional<TreatmentProcedure> treatmentProcedure = treatmentProcedureService.getTreatmentProcedureByDoctorIdAndPatientId(doctorID, patientID);
        return treatmentProcedure.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Create a new TreatmentProcedure record
    @PostMapping
    public ResponseEntity<TreatmentProcedure> createTreatmentProcedure(@RequestBody TreatmentProcedure treatmentProcedure) {
        int result = treatmentProcedureService.createTreatmentProcedure(treatmentProcedure);
        if (result > 0) {
            return ResponseEntity.status(201).body(treatmentProcedure); // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    // Delete a TreatmentProcedure record by doctorID and patientID
    @DeleteMapping("/{doctorID}/{patientID}")
    public ResponseEntity<Void> deleteTreatmentProcedure(@PathVariable int doctorID, @PathVariable int patientID) {
        int result = treatmentProcedureService.deleteTreatmentProcedure(doctorID, patientID);
        if (result > 0) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
