package com.proj.mideval.controller;

import com.proj.mideval.model.Appointment;
import com.proj.mideval.model.GrantAppointmentRequest;
import com.proj.mideval.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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

    // Doctor grants an appointment request, creates a bill
    @PutMapping("/doctor/{doctorID}/grant")
    public ResponseEntity<String> grantAppointment(@RequestBody GrantAppointmentRequest request) {
        int result = appointmentService.grantAppointmentRequest(
                request.getAppointmentID(),
                request.getAppointmentTime(),
                request.getPatientID(),
                request.getTotalCost(),
                request.getType()
        );

        if (result > 0) {
            return ResponseEntity.ok("Appointment successfully granted and bill created.");
        } else {
            return ResponseEntity.badRequest().body("Failed to grant appointment.");
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

    // Get upcoming appointments of a doctor with Bill status 1
     @GetMapping("/doctor/{doctorID}/upcoming/billStatus1")
    public ResponseEntity<List<Appointment>> getUpcomingAppointmentsForDoctorWithBillIdStatus1(@PathVariable int doctorID) {
        List<Appointment> appointments = appointmentService.getUpcomingAppointmentsForDoctorWithBillIdStatus1(doctorID);
        return ResponseEntity.ok(appointments);
    }

    // Get requested appointments of a doctor
    @GetMapping("/doctor/{doctorID}/requested")
    public ResponseEntity<List<Appointment>> getRequestedAppointmentsForDoctor(@PathVariable int doctorID) {
        List<Appointment> appointments = appointmentService.getRequestedAppointmentsForDoctor(doctorID);
        return ResponseEntity.ok(appointments);
    }


    // Update the time of an appointment
    @PutMapping("/{appointmentID}/update-time")
    public ResponseEntity<String> updateAppointmentTime(@PathVariable int appointmentID, @RequestParam String newTime) {
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date parsedDate;

        try {
            parsedDate = isoFormat.parse(newTime);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Please use ISO 8601 format.");
        }

        int result = appointmentService.updateAppointmentTime(appointmentID, parsedDate);
        if (result > 0) {
            return ResponseEntity.ok("Appointment time successfully updated.");
        } else {
            return ResponseEntity.badRequest().body("Failed to update appointment time. Ensure the new time is in the future.");
        }
    }


    // Delete an appointment (to be done by the patient)
    @DeleteMapping("/{appointmentID}/delete")
    public ResponseEntity<String> deleteAppointment(@PathVariable int appointmentID) {
        int result = appointmentService.deleteAppointment(appointmentID);

        if (result > 0) {
//            System.out.println(appointmentID);
            return ResponseEntity.ok("Appointment successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found or could not be deleted.");
        }
    }



    // Update prescription for an appointment
    @PutMapping("/{appointmentID}/update-prescription")
    public ResponseEntity<String> updateAppointmentPrescription(@PathVariable int appointmentID, @RequestParam String prescription) {
        int result = appointmentService.updateAppointmentPrescription(appointmentID, prescription);
        if (result > 0) {
            return ResponseEntity.ok("Prescription successfully updated.");
        } else {
            return ResponseEntity.badRequest().body("Failed to update prescription. Ensure the appointment ID is valid.");
        }
    }



}
