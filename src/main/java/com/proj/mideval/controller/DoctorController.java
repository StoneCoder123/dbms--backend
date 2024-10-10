package com.proj.mideval.controller;

import com.proj.mideval.model.Doctor;
import com.proj.mideval.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{doctorID}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable int doctorID) {
        Optional<Doctor> doctor = doctorService.getDoctorById(doctorID);
        return doctor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        int result = doctorService.createDoctor(doctor);
        if (result > 0) {
            return ResponseEntity.status(201).body(doctor);  // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{doctorID}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable int doctorID, @RequestBody Doctor doctor) {
        int result = doctorService.updateDoctor(doctorID, doctor);
        if (result > 0) {
            return ResponseEntity.ok(doctor);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{doctorID}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable int doctorID) {
        int result = doctorService.deleteDoctor(doctorID);
        if (result > 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
