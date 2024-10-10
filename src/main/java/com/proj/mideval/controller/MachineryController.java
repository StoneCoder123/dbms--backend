package com.proj.mideval.controller;

import com.proj.mideval.model.Machinery;
import com.proj.mideval.service.MachineryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/machinery")
public class MachineryController {

    @Autowired
    private MachineryService machineryService;

    @GetMapping
    public List<Machinery> getAllMachinery() {
        return machineryService.getAllMachinery();
    }

    @GetMapping("/{machineID}")
    public ResponseEntity<Machinery> getMachineryById(@PathVariable int machineID) {
        Optional<Machinery> machinery = machineryService.getMachineryById(machineID);
        return machinery.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Machinery> createMachinery(@RequestBody Machinery machinery) {
        int result = machineryService.createMachinery(machinery);
        if (result > 0) {
            return ResponseEntity.status(201).body(machinery);  // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{machineID}")
    public ResponseEntity<Machinery> updateMachinery(@PathVariable int machineID, @RequestBody Machinery machinery) {
        int result = machineryService.updateMachinery(machineID, machinery);
        if (result > 0) {
            return ResponseEntity.ok(machinery);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{machineID}")
    public ResponseEntity<Void> deleteMachinery(@PathVariable int machineID) {
        int result = machineryService.deleteMachinery(machineID);
        if (result > 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
