package com.proj.mideval.controller;

import com.proj.mideval.model.Appointment;
import com.proj.mideval.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{appointmentID}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable int appointmentID) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(appointmentID);
        return appointment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        int result = appointmentService.createAppointment(appointment);
        if (result > 0) {
            return ResponseEntity.status(201).body(appointment);  // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{appointmentID}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable int appointmentID, @RequestBody Appointment appointment) {
        int result = appointmentService.updateAppointment(appointmentID, appointment);
        if (result > 0) {
            return ResponseEntity.ok(appointment);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{appointmentID}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable int appointmentID) {
        int result = appointmentService.deleteAppointment(appointmentID);
        if (result > 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
