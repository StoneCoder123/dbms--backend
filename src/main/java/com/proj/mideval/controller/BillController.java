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

    // Get all bills
    @GetMapping
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }

    // Get a bill by ID
    @GetMapping("/{billID}")
    public ResponseEntity<Bill> getBillById(@PathVariable int billID) {
        Optional<Bill> bill = billService.getBillById(billID);
        return bill.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // 1) Create a new bill
    @PostMapping
    public ResponseEntity<Bill> createBill(@RequestParam int patientID, @RequestParam int totalCost, @RequestParam String type) {
        int result = billService.createBill(patientID, totalCost, type);
        if (result > 0) {
            Bill createdBill = new Bill();
            createdBill.setPatientID(patientID);
            createdBill.setTotalCost(totalCost);
            createdBill.setType(type);
            createdBill.setStatus(0); // Default status
            return ResponseEntity.status(201).body(createdBill);  // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    // Get bills by patient ID
    @GetMapping("/patient/{patientID}")
    public List<Bill> getBillByPatientID(@PathVariable int patientID) {
        return billService.getBillByPatientID(patientID);
    }

    // Get bills with status1
    @GetMapping("/status1")
    public List<Bill> getBillByStatus1() {
        return billService.getBillByStatus1();
    }

    // 3) Update the status of a bill
    @PatchMapping("/{billID}/status")
    public ResponseEntity<Void> updateBillStatus(@PathVariable int billID, @RequestParam int status) {
        System.out.println("Entering updateBillStatus method"); // Add this line for debugging
        System.out.println("Bill ID: " + billID + ", Status: " + status);

        int result = billService.updateBillStatus(billID, status);
        System.out.println(result);
        if (result > 0) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }

    // 2) Delete a bill
    @DeleteMapping("/{billID}")
    public ResponseEntity<Void> deleteBill(@PathVariable int billID) {
        int result = billService.deleteBill(billID);
        if (result > 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }




    // Get unpaid bills by patient ID
    @GetMapping("/patient/{patientID}/unpaid")
    public List<Bill> getUnpaidBillsByPatientID(@PathVariable int patientID) {
        return billService.getBillByPatientID_unpaid_ones(patientID);
    }
}
