package com.proj.mideval.controller;

import com.proj.mideval.model.StaffOperates;
import com.proj.mideval.service.StaffOperatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/staff-operates")
public class StaffOperatesController {

    @Autowired
    private StaffOperatesService staffOperatesService;

    // Retrieve all staff operations
    @GetMapping
    public List<StaffOperates> getAllStaffOperates() {
        return staffOperatesService.getAllStaffOperates();
    }

    // Retrieve a staff operation by machineID and staffID
    @GetMapping("/{machineID}/{staffID}")
    public ResponseEntity<StaffOperates> getStaffOperatesByIds(@PathVariable int machineID, @PathVariable int staffID) {
        Optional<StaffOperates> staffOperates = staffOperatesService.getStaffOperatesByIds(machineID, staffID);
        return staffOperates.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Create a new staff operation
    @PostMapping
    public ResponseEntity<StaffOperates> createStaffOperates(@RequestBody StaffOperates staffOperates) {
        int result = staffOperatesService.createStaffOperates(staffOperates);
        if (result > 0) {
            return ResponseEntity.status(201).body(staffOperates); // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    // Delete a staff operation
    @DeleteMapping("/{machineID}/{staffID}")
    public ResponseEntity<Void> deleteStaffOperates(@PathVariable int machineID, @PathVariable int staffID) {
        int result = staffOperatesService.deleteStaffOperates(machineID, staffID);
        if (result > 0) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
