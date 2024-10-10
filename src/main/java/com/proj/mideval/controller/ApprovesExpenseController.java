package com.proj.mideval.controller;

import com.proj.mideval.model.ApprovesExpense;
import com.proj.mideval.service.ApprovesExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/approvedExpense")
public class ApprovesExpenseController {

    @Autowired
    private ApprovesExpenseService approvesExpenseService;

    @GetMapping
    public List<ApprovesExpense> getAllApprovedExpenses() {
        return approvesExpenseService.getAllApprovedExpenses();
    }

    @GetMapping("/{adminID}/{deptID}")
    public ResponseEntity<ApprovesExpense> getApprovedExpenseById(@PathVariable int adminID, @PathVariable int deptID) {
        Optional<ApprovesExpense> approvesExpense = approvesExpenseService.getApprovedExpenseById(adminID, deptID);
        return approvesExpense.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ApprovesExpense> createApprovedExpense(@RequestBody ApprovesExpense approvesExpense) {
        int result = approvesExpenseService.createApprovedExpense(approvesExpense);
        if (result > 0) {
            return ResponseEntity.status(201).body(approvesExpense);  // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{adminID}/{deptID}")
    public ResponseEntity<ApprovesExpense> updateApprovedExpense(@PathVariable int adminID, @PathVariable int deptID, @RequestBody ApprovesExpense approvesExpense) {
        int result = approvesExpenseService.updateApprovedExpense(adminID, deptID, approvesExpense);
        if (result > 0) {
            return ResponseEntity.ok(approvesExpense);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{adminID}/{deptID}")
    public ResponseEntity<Void> deleteApprovedExpense(@PathVariable int adminID, @PathVariable int deptID) {
        int result = approvesExpenseService.deleteApprovedExpense(adminID, deptID);
        if (result > 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
