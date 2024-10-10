package com.proj.mideval.controller;

import com.proj.mideval.model.TakeWorkerSal;
import com.proj.mideval.service.TakeWorkerSalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/takeWorkerSal")
public class TakeWorkerSalController {

    @Autowired
    private TakeWorkerSalService takeWorkerSalService;

    // Retrieve all TakeWorkerSal records
    @GetMapping
    public List<TakeWorkerSal> getAllTakeWorkerSal() {
        return takeWorkerSalService.getAllTakeWorkerSal();
    }

    // Retrieve a TakeWorkerSal record by workerID and date
    @GetMapping("/{workerID}/{date}")
    public ResponseEntity<TakeWorkerSal> getTakeWorkerSalByWorkerIdAndDate(@PathVariable String workerID, @PathVariable Date date) {
        Optional<TakeWorkerSal> takeWorkerSal = takeWorkerSalService.getTakeWorkerSalByWorkerIdAndDate(workerID, date);
        return takeWorkerSal.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Create a new TakeWorkerSal record
    @PostMapping
    public ResponseEntity<TakeWorkerSal> createTakeWorkerSal(@RequestBody TakeWorkerSal takeWorkerSal) {
        int result = takeWorkerSalService.createTakeWorkerSal(takeWorkerSal);
        if (result > 0) {
            return ResponseEntity.status(201).body(takeWorkerSal); // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    // Delete a TakeWorkerSal record by workerID and date
    @DeleteMapping("/{workerID}/{date}")
    public ResponseEntity<Void> deleteTakeWorkerSal(@PathVariable String workerID, @PathVariable Date date) {
        int result = takeWorkerSalService.deleteTakeWorkerSal(workerID, date);
        if (result > 0) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
