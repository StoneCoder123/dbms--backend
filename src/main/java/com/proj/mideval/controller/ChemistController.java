package com.proj.mideval.controller;

import com.proj.mideval.model.Chemist;
import com.proj.mideval.service.ChemistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chemist")
public class ChemistController {

    @Autowired
    private ChemistService chemistService;

    @GetMapping
    public List<Chemist> getAllChemists() {
        return chemistService.getAllChemists();
    }

    @GetMapping("/{chemistID}")
    public ResponseEntity<Chemist> getChemistById(@PathVariable int chemistID) {
        Optional<Chemist> chemist = chemistService.getChemistById(chemistID);
        return chemist.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Chemist> createChemist(@RequestBody Chemist chemist) {
        int result = chemistService.createChemist(chemist);
        if (result > 0) {
            return ResponseEntity.status(201).body(chemist);  // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{chemistID}")
    public ResponseEntity<Chemist> updateChemist(@PathVariable int chemistID, @RequestBody Chemist chemist) {
        int result = chemistService.updateChemist(chemistID, chemist);
        if (result > 0) {
            return ResponseEntity.ok(chemist);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{chemistID}")
    public ResponseEntity<Void> deleteChemist(@PathVariable int chemistID) {
        int result = chemistService.deleteChemist(chemistID);
        if (result > 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
