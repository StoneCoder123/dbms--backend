
package com.proj.mideval.controller;

import com.proj.mideval.model.Appointment;
import com.proj.mideval.model.Surgery;
import com.proj.mideval.model.SurgeryRequest;
import com.proj.mideval.service.SurgeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/surgeries")
public class SurgeryController {

    @Autowired
    private SurgeryService surgeryService;

    // Retrieve all Surgery records
    @GetMapping
    public List<Surgery> getAllSurgeries() {
        return surgeryService.getAllSurgeries();
    }

    // Retrieve a Surgery record by surgeryID
    @GetMapping("/{surgeryID}")
    public ResponseEntity<Surgery> getSurgeryById(@PathVariable int surgeryID) {
        Optional<Surgery> surgery = surgeryService.getSurgeryById(surgeryID);
        return surgery.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Retrieve all Surgery records by doctorID
    @GetMapping("/doctor/{doctorID}")
    public List<Surgery> getSurgeriesByDoctorID(@PathVariable int doctorID) {
        return surgeryService.getSurgeryByDoctorID(doctorID);
    }

    // Retrieve all Surgery records by patientID
    @GetMapping("/patient/{patientID}")
    public List<Surgery> getSurgeriesByPatientID(@PathVariable int patientID) {
        return surgeryService.getSurgeryByPatientID(patientID);
    }

//    // Create a new Surgery record
//    @PostMapping
//    public ResponseEntity<Integer> createSurgery(
//            @RequestParam int patientID,
//            @RequestParam int doctorID,
//            @RequestParam int totalCost,
//            @RequestParam int criticalLevel,
//            @RequestParam Date time) {
//        int surgeryID = surgeryService.createSurgery(patientID, doctorID, totalCost, criticalLevel, time);
//        return ResponseEntity.ok(surgeryID);
//    }


    @PostMapping
    public ResponseEntity<Integer> createSurgery(@RequestBody SurgeryRequest surgeryRequest) {
        int surgeryID = surgeryService.createSurgery(
                surgeryRequest.getPatientID(),
                surgeryRequest.getDoctorID(),
                surgeryRequest.getTotalCost(),
                surgeryRequest.getCriticalLevel(),
                surgeryRequest.getTime()
        );
        return ResponseEntity.ok(surgeryID);
    }



    // Delete a Surgery record by doctorID and surgeryID
    @DeleteMapping("/{surgeryID}/doctor/{doctorID}")
    public ResponseEntity<Void> deleteSurgeryByDoctor(@PathVariable int surgeryID, @PathVariable int doctorID) {
        int rowsAffected = surgeryService.deleteSurgeryByDoctor(surgeryID, doctorID);
        return rowsAffected > 0 ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

//    // Reschedule a Surgery by doctorID and surgeryID
//    @PutMapping("/{surgeryID}/doctor/{doctorID}")
//    public ResponseEntity<Void> rescheduleSurgery(
//            @PathVariable int surgeryID,
//            @PathVariable int doctorID,
//            @RequestParam Date newTime) {
//        int rowsAffected = surgeryService.rescheduleSurgery(surgeryID, doctorID, newTime);
//        return rowsAffected > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
//    }


    // Reschedule a Surgery by doctorID and surgeryID
    @PutMapping("/{surgeryID}/doctor/{doctorID}")
    public ResponseEntity<Void> rescheduleSurgery(
            @PathVariable int surgeryID,
            @PathVariable int doctorID,
            @RequestBody SurgeryRequest surgeryRequest) {

        // Get the new time from the DTO
        Date newTime = surgeryRequest.getTime(); // Assuming you have a 'getTime' method in the DTO

        int rowsAffected = surgeryService.rescheduleSurgery(surgeryID, doctorID, newTime);
        return rowsAffected > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // Get upcoming appointments of a doctor with Bill status 1
    @GetMapping("/doctor/{doctorID}/upcoming/billStatus1")
    public ResponseEntity<List<Surgery>> getUpcomingAppointmentsForDoctorWithBillIdStatus1(@PathVariable int doctorID) {
        List<Surgery> surgeries = surgeryService.getUpcomingSurgeriesForDoctorWithBillIdStatus1(doctorID);
        return ResponseEntity.ok(surgeries);
    }

}
