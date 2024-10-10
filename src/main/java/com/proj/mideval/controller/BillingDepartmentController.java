package com.proj.mideval.controller;

import com.proj.mideval.model.BillingDepartment;
import com.proj.mideval.service.BillingDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/billingDepartment")
public class BillingDepartmentController {

    @Autowired
    private BillingDepartmentService billingDepartmentService;

    @GetMapping
    public List<BillingDepartment> getAllBillingDepartments() {
        return billingDepartmentService.getAllBillingDepartments();
    }

    @GetMapping("/{deptID}")
    public ResponseEntity<BillingDepartment> getBillingDepartmentById(@PathVariable int deptID) {
        Optional<BillingDepartment> billingDepartment = billingDepartmentService.getBillingDepartmentById(deptID);
        return billingDepartment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BillingDepartment> createBillingDepartment(@RequestBody BillingDepartment billingDepartment) {
        int result = billingDepartmentService.createBillingDepartment(billingDepartment);
        if (result > 0) {
            return ResponseEntity.status(201).body(billingDepartment);  // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{deptID}")
    public ResponseEntity<BillingDepartment> updateBillingDepartment(@PathVariable int deptID, @RequestBody BillingDepartment billingDepartment) {
        int result = billingDepartmentService.updateBillingDepartment(deptID, billingDepartment);
        if (result > 0) {
            return ResponseEntity.ok(billingDepartment);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{deptID}")
    public ResponseEntity<Void> deleteBillingDepartment(@PathVariable int deptID) {
        int result = billingDepartmentService.deleteBillingDepartment(deptID);
        if (result > 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
