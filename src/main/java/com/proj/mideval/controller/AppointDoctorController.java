package com.proj.mideval.controller;

import com.proj.mideval.model.AppointDoctor;
import com.proj.mideval.service.AppointDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointDoctor")
public class AppointDoctorController {

    @Autowired
    private AppointDoctorService appointDoctorService;

    @GetMapping
    public List<AppointDoctor> getAllAppointDoctors() {
        return appointDoctorService.getAllAppointDoctors();
    }

    @GetMapping("/{adminID}/{doctorID}")
    public ResponseEntity<AppointDoctor> getAppointDoctorById(@PathVariable int adminID, @PathVariable int doctorID) {
        Optional<AppointDoctor> appointDoctor = appointDoctorService.getAppointDoctorById(adminID, doctorID);
        return appointDoctor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AppointDoctor> createAppointDoctor(@RequestBody AppointDoctor appointDoctor) {
        int result = appointDoctorService.createAppointDoctor(appointDoctor);
        if (result > 0) {
            return ResponseEntity.status(201).body(appointDoctor);  // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{adminID}/{doctorID}")
    public ResponseEntity<AppointDoctor> updateAppointDoctor(@PathVariable int adminID, @PathVariable int doctorID, @RequestBody AppointDoctor appointDoctor) {
        int result = appointDoctorService.updateAppointDoctor(adminID, doctorID, appointDoctor);
        if (result > 0) {
            return ResponseEntity.ok(appointDoctor);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{adminID}/{doctorID}")
    public ResponseEntity<Void> deleteAppointDoctor(@PathVariable int adminID, @PathVariable int doctorID) {
        int result = appointDoctorService.deleteAppointDoctor(adminID, doctorID);
        if (result > 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
