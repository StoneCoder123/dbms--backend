package com.proj.mideval.controller;

import com.proj.mideval.model.Patient;
import com.proj.mideval.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{patientID}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int patientID) {
        Optional<Patient> patient = patientService.getPatientById(patientID);
        return patient.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        int result = patientService.createPatient(patient);
        if (result > 0) {
            return ResponseEntity.status(201).body(patient);  // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{patientID}")
    public ResponseEntity<Patient> updatePatient(@PathVariable int patientID, @RequestBody Patient patient) {
        int result = patientService.updatePatient(patientID, patient);
        if (result > 0) {
            return ResponseEntity.ok(patient);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{patientID}")
    public ResponseEntity<Void> deletePatient(@PathVariable int patientID) {
        int result = patientService.deletePatient(patientID);
        if (result > 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
