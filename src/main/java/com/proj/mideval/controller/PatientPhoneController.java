package com.proj.mideval.controller;

import com.proj.mideval.model.PatientPhone;
import com.proj.mideval.service.PatientPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patientphones")
public class PatientPhoneController {

    @Autowired
    private PatientPhoneService patientPhoneService;

    @GetMapping
    public List<PatientPhone> getAllPatientPhones() {
        return patientPhoneService.getAllPatientPhones();
    }

    @GetMapping("/{patientID}")
    public ResponseEntity<PatientPhone> getPatientPhoneById(@PathVariable int patientID) {
        Optional<PatientPhone> patientPhone = patientPhoneService.getPatientPhoneById(patientID);
        return patientPhone.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PatientPhone> createPatientPhone(@RequestBody PatientPhone patientPhone) {
        int result = patientPhoneService.createPatientPhone(patientPhone);
        if (result > 0) {
            return ResponseEntity.status(201).body(patientPhone);  // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{patientID}")
    public ResponseEntity<PatientPhone> updatePatientPhone(@PathVariable int patientID, @RequestBody PatientPhone patientPhone) {
        int result = patientPhoneService.updatePatientPhone(patientID, patientPhone);
        if (result > 0) {
            return ResponseEntity.ok(patientPhone);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{patientID}")
    public ResponseEntity<Void> deletePatientPhone(@PathVariable int patientID) {
        int result = patientPhoneService.deletePatientPhone(patientID);
        if (result > 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
