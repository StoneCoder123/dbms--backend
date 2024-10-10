package com.proj.mideval.controller;

import com.proj.mideval.model.ExpenditureDepartment;
import com.proj.mideval.service.ExpenditureDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/expenditure-departments")
public class ExpenditureDepartmentController {

    @Autowired
    private ExpenditureDepartmentService expenditureDepartmentService;

    @GetMapping
    public List<ExpenditureDepartment> getAllExpenditureDepartments() {
        return expenditureDepartmentService.getAllExpenditureDepartments();
    }

    @GetMapping("/{deptID}")
    public ResponseEntity<ExpenditureDepartment> getExpenditureDepartmentById(@PathVariable int deptID) {
        Optional<ExpenditureDepartment> expenditureDepartment = expenditureDepartmentService.getExpenditureDepartmentById(deptID);
        return expenditureDepartment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ExpenditureDepartment> createExpenditureDepartment(@RequestBody ExpenditureDepartment expenditureDepartment) {
        int result = expenditureDepartmentService.createExpenditureDepartment(expenditureDepartment);
        if (result > 0) {
            return ResponseEntity.status(201).body(expenditureDepartment);  // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{deptID}")
    public ResponseEntity<ExpenditureDepartment> updateExpenditureDepartment(@PathVariable int deptID, @RequestBody ExpenditureDepartment expenditureDepartment) {
        int result = expenditureDepartmentService.updateExpenditureDepartment(deptID, expenditureDepartment);
        if (result > 0) {
            return ResponseEntity.ok(expenditureDepartment);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{deptID}")
    public ResponseEntity<Void> deleteExpenditureDepartment(@PathVariable int deptID) {
        int result = expenditureDepartmentService.deleteExpenditureDepartment(deptID);
        if (result > 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
