package com.proj.mideval.controller;

import com.proj.mideval.model.Salary;
import com.proj.mideval.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salaries")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @GetMapping
    public List<Salary> getAllSalaries() {
        return salaryService.getAllSalaries();
    }

    @GetMapping("/{workerID}")
    public ResponseEntity<Salary> getSalaryById(@PathVariable String workerID) {
        Optional<Salary> salary = salaryService.getSalaryById(workerID);
        return salary.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Salary> createSalary(@RequestBody Salary salary) {
        int result = salaryService.createSalary(salary);
        if (result > 0) {
            return ResponseEntity.status(201).body(salary);  // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{workerID}")
    public ResponseEntity<Salary> updateSalary(@PathVariable String workerID, @RequestBody Salary salary) {
        int result = salaryService.updateSalary(workerID, salary);
        if (result > 0) {
            return ResponseEntity.ok(salary);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{workerID}")
    public ResponseEntity<Void> deleteSalary(@PathVariable String workerID) {
        int result = salaryService.deleteSalary(workerID);
        if (result > 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
