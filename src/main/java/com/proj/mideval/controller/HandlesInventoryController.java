package com.proj.mideval.controller;

import com.proj.mideval.model.HandlesInventory;
import com.proj.mideval.service.HandlesInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/handles-inventory")
public class HandlesInventoryController {

    @Autowired
    private HandlesInventoryService handlesInventoryService;

    @GetMapping
    public List<HandlesInventory> getAllHandlesInventory() {
        return handlesInventoryService.getAllHandlesInventory();
    }

    @GetMapping("/{medicineID}/{chemistID}")
    public ResponseEntity<HandlesInventory> getHandlesInventoryById(@PathVariable int medicineID, @PathVariable int chemistID) {
        Optional<HandlesInventory> handlesInventory = handlesInventoryService.getHandlesInventoryById(medicineID, chemistID);
        return handlesInventory.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HandlesInventory> createHandlesInventory(@RequestBody HandlesInventory handlesInventory) {
        int result = handlesInventoryService.createHandlesInventory(handlesInventory);
        if (result > 0) {
            return ResponseEntity.status(201).body(handlesInventory);  // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{medicineID}/{chemistID}")
    public ResponseEntity<HandlesInventory> updateHandlesInventory(@PathVariable int medicineID, @PathVariable int chemistID, @RequestBody HandlesInventory handlesInventory) {
        int result = handlesInventoryService.updateHandlesInventory(medicineID, chemistID, handlesInventory);
        if (result > 0) {
            return ResponseEntity.ok(handlesInventory);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{medicineID}/{chemistID}")
    public ResponseEntity<Void> deleteHandlesInventory(@PathVariable int medicineID, @PathVariable int chemistID) {
        int result = handlesInventoryService.deleteHandlesInventory(medicineID, chemistID);
        if (result > 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
