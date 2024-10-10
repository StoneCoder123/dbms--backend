package com.proj.mideval.controller;

import com.proj.mideval.model.TakesCare;
import com.proj.mideval.service.TakesCareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/takesCare")
public class TakesCareController {

    @Autowired
    private TakesCareService takesCareService;

    // Retrieve all TakesCare records
    @GetMapping
    public List<TakesCare> getAllTakesCare() {
        return takesCareService.getAllTakesCare();
    }

    // Retrieve a TakesCare record by patientID
    @GetMapping("/{patientID}")
    public ResponseEntity<TakesCare> getTakesCareByPatientId(@PathVariable int patientID) {
        Optional<TakesCare> takesCare = takesCareService.getTakesCareByPatientId(patientID);
        return takesCare.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Create a new TakesCare record
    @PostMapping
    public ResponseEntity<TakesCare> createTakesCare(@RequestBody TakesCare takesCare) {
        int result = takesCareService.createTakesCare(takesCare);
        if (result > 0) {
            return ResponseEntity.status(201).body(takesCare); // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    // Delete a TakesCare record by patientID
    @DeleteMapping("/{patientID}")
    public ResponseEntity<Void> deleteTakesCare(@PathVariable int patientID) {
        int result = takesCareService.deleteTakesCare(patientID);
        if (result > 0) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
