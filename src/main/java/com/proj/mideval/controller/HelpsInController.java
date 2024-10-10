package com.proj.mideval.controller;

import com.proj.mideval.model.HelpsIn;
import com.proj.mideval.service.HelpsInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/helps-in")
public class HelpsInController {

    @Autowired
    private HelpsInService helpsInService;

    @GetMapping
    public List<HelpsIn> getAllHelpsIn() {
        return helpsInService.getAllHelpsIn();
    }

    @GetMapping("/{surgeryID}/{staffID}")
    public ResponseEntity<HelpsIn> getHelpsInById(@PathVariable int surgeryID, @PathVariable int staffID) {
        Optional<HelpsIn> helpsIn = helpsInService.getHelpsInById(surgeryID, staffID);
        return helpsIn.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HelpsIn> createHelpsIn(@RequestBody HelpsIn helpsIn) {
        int result = helpsInService.createHelpsIn(helpsIn);
        if (result > 0) {
            return ResponseEntity.status(201).body(helpsIn);  // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{surgeryID}/{staffID}")
    public ResponseEntity<HelpsIn> updateHelpsIn(@PathVariable int surgeryID, @PathVariable int staffID, @RequestBody HelpsIn helpsIn) {
        int result = helpsInService.updateHelpsIn(surgeryID, staffID, helpsIn);
        if (result > 0) {
            return ResponseEntity.ok(helpsIn);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{surgeryID}/{staffID}")
    public ResponseEntity<Void> deleteHelpsIn(@PathVariable int surgeryID, @PathVariable int staffID) {
        int result = helpsInService.deleteHelpsIn(surgeryID, staffID);
        if (result > 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
