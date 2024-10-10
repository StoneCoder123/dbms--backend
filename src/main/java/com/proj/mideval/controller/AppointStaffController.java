package com.proj.mideval.controller;

import com.proj.mideval.model.AppointStaff;
import com.proj.mideval.service.AppointStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointStaff")
public class AppointStaffController {

    @Autowired
    private AppointStaffService appointStaffService;

    @GetMapping
    public List<AppointStaff> getAllAppointStaffs() {
        return appointStaffService.getAllAppointStaffs();
    }

    @GetMapping("/{adminID}/{staffID}")
    public ResponseEntity<AppointStaff> getAppointStaffById(@PathVariable int adminID, @PathVariable int staffID) {
        Optional<AppointStaff> appointStaff = appointStaffService.getAppointStaffById(adminID, staffID);
        return appointStaff.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AppointStaff> createAppointStaff(@RequestBody AppointStaff appointStaff) {
        int result = appointStaffService.createAppointStaff(appointStaff);
        if (result > 0) {
            return ResponseEntity.status(201).body(appointStaff);  // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{adminID}/{staffID}")
    public ResponseEntity<AppointStaff> updateAppointStaff(@PathVariable int adminID, @PathVariable int staffID, @RequestBody AppointStaff appointStaff) {
        int result = appointStaffService.updateAppointStaff(adminID, staffID, appointStaff);
        if (result > 0) {
            return ResponseEntity.ok(appointStaff);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{adminID}/{staffID}")
    public ResponseEntity<Void> deleteAppointStaff(@PathVariable int adminID, @PathVariable int staffID) {
        int result = appointStaffService.deleteAppointStaff(adminID, staffID);
        if (result > 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
