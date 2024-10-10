package com.proj.mideval.controller;

import com.proj.mideval.model.Staff;
import com.proj.mideval.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    // Retrieve all staff members
    @GetMapping
    public List<Staff> getAllStaff() {
        return staffService.getAllStaff();
    }

    // Retrieve a staff member by ID
    @GetMapping("/{staffID}")
    public ResponseEntity<Staff> getStaffById(@PathVariable int staffID) {
        Optional<Staff> staff = staffService.getStaffById(staffID);
        return staff.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Create a new staff member
    @PostMapping
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
        int result = staffService.createStaff(staff);
        if (result > 0) {
            return ResponseEntity.status(201).body(staff); // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    // Update a staff member's information
    @PutMapping("/{staffID}")
    public ResponseEntity<Staff> updateStaff(@PathVariable int staffID, @RequestBody Staff staff) {
        int result = staffService.updateStaff(staffID, staff);
        if (result > 0) {
            return ResponseEntity.ok(staff);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a staff member
    @DeleteMapping("/{staffID}")
    public ResponseEntity<Void> deleteStaff(@PathVariable int staffID) {
        int result = staffService.deleteStaff(staffID);
        if (result > 0) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
