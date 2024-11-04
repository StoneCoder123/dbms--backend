package com.proj.mideval.controller;

import com.proj.mideval.model.Medicines;
import com.proj.mideval.service.MedicinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicines")
public class MedicinesController {

    @Autowired
    private MedicinesService medicinesService;

    @GetMapping
    public List<Medicines> getAllMedicines() {
        return medicinesService.getAllMedicines();
    }

    @GetMapping("/{medicineID}")
    public ResponseEntity<Medicines> getMedicinesById(@PathVariable int medicineID) {
        Optional<Medicines> medicines = medicinesService.getMedicinesById(medicineID);
        return medicines.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Medicines> createMedicines(@RequestBody Medicines medicines) {
        int result = medicinesService.createMedicines(medicines);
        if (result > 0) {
            return ResponseEntity.status(201).body(medicines);  // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{medicineID}")
    public ResponseEntity<Medicines> updateMedicines(@PathVariable int medicineID, @RequestBody Medicines medicines) {
        int result = medicinesService.updateMedicines(medicineID, medicines);
        if (result > 0) {
            return ResponseEntity.ok(medicines);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{medicineID}")
    public ResponseEntity<Void> deleteMedicines(@PathVariable int medicineID) {
        int result = medicinesService.deleteMedicines(medicineID);
        if (result > 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
