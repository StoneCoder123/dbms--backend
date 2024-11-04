package com.proj.mideval.controller;

import com.proj.mideval.model.TakesDoctorSal;
import com.proj.mideval.service.TakesDoctorSalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctor-salaries")
public class TakesDoctorSalController {

    @Autowired
    private TakesDoctorSalService takesDoctorSalService;

    // Retrieve all doctor salary records
    @GetMapping
    public List<TakesDoctorSal> getAllDoctorSalaries() {
        return takesDoctorSalService.getAllDoctorSalaries();
    }



    // Retrieve a doctor salary record by ID
    @GetMapping("/{dsID}")
    public ResponseEntity<TakesDoctorSal> getDoctorSalaryById(@PathVariable int dsID) {
        Optional<TakesDoctorSal> doctorSal = takesDoctorSalService.getDoctorSalaryById(dsID);
        return doctorSal.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/take/{doctorID}")
    public ResponseEntity<List<TakesDoctorSal>> getDoctorSalariesByDoctorID(@PathVariable int doctorID) {
        List<TakesDoctorSal> doctorSalaries = takesDoctorSalService.getDoctorSalaryByDoctorID(doctorID);
        if (doctorSalaries.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(doctorSalaries);
    }


    // Create a new doctor salary record
    @PostMapping
    public ResponseEntity<TakesDoctorSal> createDoctorSalary(@RequestBody TakesDoctorSal doctorSal) {
        int result = takesDoctorSalService.createDoctorSalary(doctorSal);
        if (result > 0) {
            return ResponseEntity.status(201).body(doctorSal); // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    // Update an existing doctor salary record
    @PutMapping("/{dsID}")
    public ResponseEntity<TakesDoctorSal> updateDoctorSalary(@PathVariable int dsID, @RequestBody TakesDoctorSal doctorSal) {
        int result = takesDoctorSalService.updateDoctorSalary(dsID, doctorSal);
        if (result > 0) {
            return ResponseEntity.ok(doctorSal);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a doctor salary record
    @DeleteMapping("/{dsID}")
    public ResponseEntity<Void> deleteDoctorSalary(@PathVariable int dsID) {
        int result = takesDoctorSalService.deleteDoctorSalary(dsID);
        if (result > 0) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
