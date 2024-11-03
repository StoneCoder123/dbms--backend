package com.proj.mideval.controller;

import com.proj.mideval.model.Bill;
import com.proj.mideval.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }

    @GetMapping("/{billID}")
    public ResponseEntity<Bill> getBillById(@PathVariable int billID) {
        Optional<Bill> bill = billService.getBillById(billID);
        return bill.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
        int result = billService.createBill(bill);
        if (result > 0) {
            return ResponseEntity.status(201).body(bill);  // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("patient/{patientID}")
    public List<Bill> getBillbyPatientID(@PathVariable int patientID){
        return billService.getgetBillByPatientID(patientID);
    }

    @PutMapping("/{billID}")
    public ResponseEntity<Bill> updateBill(@PathVariable int billID, @RequestBody Bill bill) {
        int result = billService.updateBill(billID, bill);
        if (result > 0) {
            return ResponseEntity.ok(bill);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{billID}")
    public ResponseEntity<Void> deleteBill(@PathVariable int billID) {
        int result = billService.deleteBill(billID);
        if (result > 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
