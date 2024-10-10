package com.proj.mideval.controller;

import com.proj.mideval.model.BuysMedicine;
import com.proj.mideval.service.BuysMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/buysMedicine")
public class BuysMedicineController {

    @Autowired
    private BuysMedicineService buysMedicineService;

    @GetMapping
    public List<BuysMedicine> getAllBuysMedicines() {
        return buysMedicineService.getAllBuysMedicines();
    }

    @GetMapping("/{patientID}/{chemistID}")
    public ResponseEntity<BuysMedicine> getBuysMedicineById(@PathVariable int patientID, @PathVariable int chemistID) {
        Optional<BuysMedicine> buysMedicine = buysMedicineService.getBuysMedicineById(patientID, chemistID);
        return buysMedicine.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BuysMedicine> createBuysMedicine(@RequestBody BuysMedicine buysMedicine) {
        int result = buysMedicineService.createBuysMedicine(buysMedicine);
        if (result > 0) {
            return ResponseEntity.status(201).body(buysMedicine);  // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{patientID}/{chemistID}")
    public ResponseEntity<BuysMedicine> updateBuysMedicine(@PathVariable int patientID, @PathVariable int chemistID, @RequestBody BuysMedicine buysMedicine) {
        int result = buysMedicineService.updateBuysMedicine(patientID, chemistID, buysMedicine);
        if (result > 0) {
            return ResponseEntity.ok(buysMedicine);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{patientID}/{chemistID}")
    public ResponseEntity<Void> deleteBuysMedicine(@PathVariable int patientID, @PathVariable int chemistID) {
        int result = buysMedicineService.deleteBuysMedicine(patientID, chemistID);
        if (result > 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
