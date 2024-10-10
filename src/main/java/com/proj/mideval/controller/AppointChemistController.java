package com.proj.mideval.controller;

import com.proj.mideval.model.AppointChemist;
import com.proj.mideval.service.AppointChemistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointChemist")
public class AppointChemistController {

    @Autowired
    private AppointChemistService appointChemistService;

    @GetMapping
    public List<AppointChemist> getAllAppointChemists() {
        return appointChemistService.getAllAppointChemists();
    }

    @GetMapping("/{adminID}/{chemistID}")
    public ResponseEntity<AppointChemist> getAppointChemistById(@PathVariable int adminID, @PathVariable int chemistID) {
        Optional<AppointChemist> appointChemist = appointChemistService.getAppointChemistById(adminID, chemistID);
        return appointChemist.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AppointChemist> createAppointChemist(@RequestBody AppointChemist appointChemist) {
        int result = appointChemistService.createAppointChemist(appointChemist);
        if (result > 0) {
            return ResponseEntity.status(201).body(appointChemist);  // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{adminID}/{chemistID}")
    public ResponseEntity<AppointChemist> updateAppointChemist(@PathVariable int adminID, @PathVariable int chemistID, @RequestBody AppointChemist appointChemist) {
        int result = appointChemistService.updateAppointChemist(adminID, chemistID, appointChemist);
        if (result > 0) {
            return ResponseEntity.ok(appointChemist);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{adminID}/{chemistID}")
    public ResponseEntity<Void> deleteAppointChemist(@PathVariable int adminID, @PathVariable int chemistID) {
        int result = appointChemistService.deleteAppointChemist(adminID, chemistID);
        if (result > 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
