package com.proj.mideval.controller;

import com.proj.mideval.model.DoctorUses;
import com.proj.mideval.service.DoctorUsesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctor-uses")
public class DoctorUsesController {

    @Autowired
    private DoctorUsesService doctorUsesService;

    @GetMapping
    public List<DoctorUses> getAllDoctorUses() {
        return doctorUsesService.getAllDoctorUses();
    }

    @GetMapping("/{doctorID}/{machineID}")
    public ResponseEntity<DoctorUses> getDoctorUsesById(@PathVariable int doctorID, @PathVariable int machineID) {
        Optional<DoctorUses> doctorUses = doctorUsesService.getDoctorUsesById(doctorID, machineID);
        return doctorUses.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DoctorUses> createDoctorUses(@RequestBody DoctorUses doctorUses) {
        int result = doctorUsesService.createDoctorUses(doctorUses);
        if (result > 0) {
            return ResponseEntity.status(201).body(doctorUses);  // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{doctorID}/{machineID}")
    public ResponseEntity<DoctorUses> updateDoctorUses(@PathVariable int doctorID, @PathVariable int machineID, @RequestBody DoctorUses doctorUses) {
        int result = doctorUsesService.updateDoctorUses(doctorID, machineID, doctorUses);
        if (result > 0) {
            return ResponseEntity.ok(doctorUses);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{doctorID}/{machineID}")
    public ResponseEntity<Void> deleteDoctorUses(@PathVariable int doctorID, @PathVariable int machineID) {
        int result = doctorUsesService.deleteDoctorUses(doctorID, machineID);
        if (result > 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
