package com.proj.mideval.controller;

import com.proj.mideval.model.WorkerIDs;
import com.proj.mideval.service.WorkerIDsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workerIDs")
public class WorkerIDsController {

    @Autowired
    private WorkerIDsService workerIDsService;

    // Retrieve all WorkerIDs records
    @GetMapping
    public List<WorkerIDs> getAllWorkerIDs() {
        return workerIDsService.getAllWorkerIDs();
    }

    // Retrieve a WorkerIDs record by workerID
    @GetMapping("/{workerID}")
    public ResponseEntity<WorkerIDs> getWorkerIDsByWorkerId(@PathVariable String workerID) {
        Optional<WorkerIDs> workerIDs = workerIDsService.getWorkerIDsByWorkerId(workerID);
        return workerIDs.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Create a new WorkerIDs record
    @PostMapping
    public ResponseEntity<WorkerIDs> createWorkerIDs(@RequestBody WorkerIDs workerIDs) {
        int result = workerIDsService.createWorkerIDs(workerIDs);
        if (result > 0) {
            return ResponseEntity.status(201).body(workerIDs); // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    // Delete a WorkerIDs record by workerID
    @DeleteMapping("/{workerID}")
    public ResponseEntity<Void> deleteWorkerIDs(@PathVariable String workerID) {
        int result = workerIDsService.deleteWorkerIDs(workerID);
        if (result > 0) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
