package com.proj.mideval.controller;

import com.proj.mideval.model.TakesChemistSal;
import com.proj.mideval.service.TakesChemistSalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chemist-salaries")
public class TakesChemistSalController {

    @Autowired
    private TakesChemistSalService takesChemistSalService;

    // Retrieve all chemist salary records
    @GetMapping
    public List<TakesChemistSal> getAllChemistSalaries() {
        return takesChemistSalService.getAllChemistSalaries();
    }

    // Retrieve a chemist salary record by ID
    @GetMapping("/{csID}")
    public ResponseEntity<TakesChemistSal> getChemistSalaryById(@PathVariable int csID) {
        Optional<TakesChemistSal> chemistSal = takesChemistSalService.getChemistSalaryById(csID);
        return chemistSal.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Retrieve chemist salary records by chemistID
    @GetMapping("/take/{chemistID}")
    public ResponseEntity<List<TakesChemistSal>> getChemistSalariesByChemistID(@PathVariable int chemistID) {
        List<TakesChemistSal> chemistSalaries = takesChemistSalService.getChemistSalaryByChemistID(chemistID);
        if (chemistSalaries.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(chemistSalaries);
    }

    // Create a new chemist salary record
    @PostMapping
    public ResponseEntity<TakesChemistSal> createChemistSalary(@RequestBody TakesChemistSal chemistSal) {
        int result = takesChemistSalService.createChemistSalary(chemistSal);
        if (result > 0) {
            return ResponseEntity.status(201).body(chemistSal); // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    // Update an existing chemist salary record
    @PutMapping("/{csID}")
    public ResponseEntity<TakesChemistSal> updateChemistSalary(@PathVariable int csID, @RequestBody TakesChemistSal chemistSal) {
        int result = takesChemistSalService.updateChemistSalary(csID, chemistSal);
        if (result > 0) {
            return ResponseEntity.ok(chemistSal);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a chemist salary record
    @DeleteMapping("/{csID}")
    public ResponseEntity<Void> deleteChemistSalary(@PathVariable int csID) {
        int result = takesChemistSalService.deleteChemistSalary(csID);
        if (result > 0) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
