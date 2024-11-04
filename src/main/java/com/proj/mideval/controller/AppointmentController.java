package com.proj.mideval.controller;

import com.proj.mideval.model.Appointment;
import com.proj.mideval.model.GrantAppointmentRequest;
import com.proj.mideval.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Patient requests an appointment with a specific doctor
    @PostMapping("/patient/{patientID}/request")
    public String requestAppointment(@PathVariable int patientID, @RequestParam int doctorID) {
        int result = appointmentService.createAppointmentRequest(patientID, doctorID);
        if (result > 0) {
            return "Appointment request successfully created.";
        } else {
            return "Failed to create appointment request.";
        }
    }

    @PutMapping("/doctor/{doctorID}/grant")
    public String grantAppointment(@RequestBody GrantAppointmentRequest request) {
        int result = appointmentService.grantAppointment(request.getAppointmentID(), request.getAppointmentTime(), request.getCost());
        if (result > 0) {
            return "Appointment successfully granted.";
        } else {
            return "Failed to grant appointment.";
        }
    }

    // Get previous appointments of a patient
    @GetMapping("/patient/{patientID}/previous")
    public ResponseEntity<List<Appointment>> getPreviousAppointmentsForPatient(@PathVariable int patientID) {
        List<Appointment> appointments = appointmentService.getPreviousAppointmentsForPatient(patientID);
        return ResponseEntity.ok(appointments);
    }

    // Get upcoming appointments of a patient
    @GetMapping("/patient/{patientID}/upcoming")
    public ResponseEntity<List<Appointment>> getUpcomingAppointmentsForPatient(@PathVariable int patientID) {
        List<Appointment> appointments = appointmentService.getUpcomingAppointmentsForPatient(patientID);
        return ResponseEntity.ok(appointments);
    }

    // Get requested appointments of a patient
    @GetMapping("/patient/{patientID}/requested")
    public ResponseEntity<List<Appointment>> getRequestedAppointmentsForPatient(@PathVariable int patientID) {
        List<Appointment> appointments = appointmentService.getRequestedAppointmentsForPatient(patientID);
        return ResponseEntity.ok(appointments);
    }

    // Get previous appointments of a doctor
    @GetMapping("/doctor/{doctorID}/previous")
    public ResponseEntity<List<Appointment>> getPreviousAppointmentsForDoctor(@PathVariable int doctorID) {
        List<Appointment> appointments = appointmentService.getPreviousAppointmentsForDoctor(doctorID);
        return ResponseEntity.ok(appointments);
    }

    // Get upcoming appointments of a doctor
    @GetMapping("/doctor/{doctorID}/upcoming")
    public ResponseEntity<List<Appointment>> getUpcomingAppointmentsForDoctor(@PathVariable int doctorID) {
        List<Appointment> appointments = appointmentService.getUpcomingAppointmentsForDoctor(doctorID);
        return ResponseEntity.ok(appointments);
    }

    // Get requested appointments of a doctor
    @GetMapping("/doctor/{doctorID}/requested")
    public ResponseEntity<List<Appointment>> getRequestedAppointmentsForDoctor(@PathVariable int doctorID) {
        List<Appointment> appointments = appointmentService.getRequestedAppointmentsForDoctor(doctorID);
        return ResponseEntity.ok(appointments);
    }
}
