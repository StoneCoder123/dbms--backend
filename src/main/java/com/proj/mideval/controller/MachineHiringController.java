package com.proj.mideval.controller;

import com.proj.mideval.model.MachineHiring;
import com.proj.mideval.service.MachineHiringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/machineHiring")
public class MachineHiringController {

    @Autowired
    private MachineHiringService machineHiringService;

    // Retrieve all MachineHiring records
    @GetMapping
    public ResponseEntity<List<MachineHiring>> getAllMachineHirings() {
        List<MachineHiring> hirings = machineHiringService.getAllMachineHirings();
        return new ResponseEntity<>(hirings, HttpStatus.OK);
    }

    // Retrieve a MachineHiring record by hiringID
    @GetMapping("/{hiringID}")
    public ResponseEntity<MachineHiring> getMachineHiringById(@PathVariable int hiringID) {
        Optional<MachineHiring> hiring = machineHiringService.getMachineHiringById(hiringID);
        return hiring.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Retrieve all MachineHiring records by doctorID
    @GetMapping("/doctor/{doctorID}")
    public ResponseEntity<List<MachineHiring>> getMachineHiringByDoctorID(@PathVariable int doctorID) {
        List<MachineHiring> hirings = machineHiringService.getMachineHiringByDoctorID(doctorID);
        return new ResponseEntity<>(hirings, HttpStatus.OK);
    }

    // Create a new MachineHiring record
    @PostMapping
    public ResponseEntity<Integer> createMachineHiring(
            @RequestParam int doctorID,
            @RequestParam int machineID,
            @RequestParam Date startDate,
            @RequestParam Date endDate) {

        int hiringID = machineHiringService.createMachineHiring(doctorID, machineID, startDate, endDate);
        return new ResponseEntity<>(hiringID, HttpStatus.CREATED);
    }

    // Delete a MachineHiring record by hiringID and doctorID
    @DeleteMapping("/{hiringID}/doctor/{doctorID}")
    public ResponseEntity<Void> deleteMachineHiringByDoctor(@PathVariable int hiringID, @PathVariable int doctorID) {
        int rowsAffected = machineHiringService.deleteMachineHiringByDoctor(hiringID, doctorID);
        if (rowsAffected > 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Extend or reschedule MachineHiring endDate by doctorID
    @PutMapping("/{hiringID}/doctor/{doctorID}/extend")
    public ResponseEntity<Void> extendMachineHiring(
            @PathVariable int hiringID,
            @PathVariable int doctorID,
            @RequestParam Date newEndDate) {

        int rowsAffected = machineHiringService.extendMachineHiring(hiringID, doctorID, newEndDate);
        if (rowsAffected > 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Add a new MachineHiring entry for a specific doctorID
    @PostMapping("/doctor/{doctorID}")
    public ResponseEntity<Integer> addMachineHiringForDoctor(
            @PathVariable int doctorID,
            @RequestBody MachineHiring machineHiring) {

        machineHiring.setDoctorID(doctorID);
        int hiringID = machineHiringService.addMachineHiringForDoctor(
                machineHiring.getDoctorID(),
                machineHiring.getMachineID(),
                machineHiring.getStartDate(),
                machineHiring.getEndDate()
        );
        return new ResponseEntity<>(hiringID, HttpStatus.CREATED);
    }

}
