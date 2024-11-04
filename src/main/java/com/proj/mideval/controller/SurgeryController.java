package com.proj.mideval.controller;

import com.proj.mideval.model.Surgery;
import com.proj.mideval.service.SurgeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/surgery")
public class SurgeryController {

    @Autowired
    private SurgeryService surgeryService;

    // Retrieve all surgeries
    @GetMapping
    public List<Surgery> getAllSurgeries() {
        return surgeryService.getAllSurgeries();
    }

    // Retrieve a surgery by surgeryID
    @GetMapping("/{surgeryID}")
    public ResponseEntity<Surgery> getSurgeryById(@PathVariable int surgeryID) {
        Optional<Surgery> surgery = surgeryService.getSurgeryById(surgeryID);
        return surgery.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Create a new surgery by doctor
    @PostMapping("/{doctorId}")
    public ResponseEntity<Surgery> createSurgery(@PathVariable int doctorId, @RequestBody Surgery surgery) {
        surgery.setDoctorID(doctorId); // Set doctorID from the path variable
        int result = surgeryService.createSurgery(surgery);
        if (result > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(surgery); // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    // Delete a surgery by doctor
    @DeleteMapping("/{doctorId}/{surgeryID}")
    public ResponseEntity<Void> deleteSurgery(@PathVariable int surgeryID, @PathVariable int doctorId) {
        int result = surgeryService.deleteSurgeryByDoctor(surgeryID, doctorId);
        if (result > 0) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoint to get all surgeries for a specific doctor
    @GetMapping("/doctor/{doctorID}")
    public List<Surgery> getSurgeriesByDoctorId(@PathVariable int doctorID) {
        return surgeryService.getSurgeryByDoctorID(doctorID);
    }

    // Reschedule a surgery by doctor
    @PutMapping("/{doctorId}/{surgeryID}/reschedule")
    public ResponseEntity<Surgery> rescheduleSurgery(@PathVariable int surgeryID,
                                                     @PathVariable int doctorId,
                                                     @RequestBody Surgery surgery) {
        int result = surgeryService.rescheduleSurgery(surgeryID, doctorId, surgery.getTime());
        if (result > 0) {
            return ResponseEntity.ok(surgery); // 200 OK
        }
        return ResponseEntity.notFound().build();
    }
}
