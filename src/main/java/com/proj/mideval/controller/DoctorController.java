package com.proj.mideval.controller;

import com.proj.mideval.model.Doctor;
import com.proj.mideval.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

//    private PasswordEncoder passwordEncoder;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DoctorController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{doctorID}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable int doctorID) {
        Optional<Doctor> doctor = doctorService.getDoctorById(doctorID);
        return doctor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        int result = doctorService.createDoctor(doctor);
        if (result > 0) {
            doctor.setPassword(null); // Hide password in response
            return ResponseEntity.status(201).body(doctor);
        }
        return ResponseEntity.badRequest().build();
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
//        String email = credentials.get("email");
//        String password = credentials.get("password");
//
//        Optional<Doctor> doctor = doctorService.getDoctorByEmail(email);
//        if (doctor.isPresent() && passwordEncoder.matches(password, doctor.get().getPassword())) {
//            String token = JwtUtil.generateToken(email, "DOCTOR");
//            return ResponseEntity.ok(token);
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//    }


    @PutMapping("/{doctorID}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable int doctorID, @RequestBody Doctor doctor) {
        int result = doctorService.updateDoctor(doctorID, doctor);
        if (result > 0) {
            doctor.setPassword(null); // Hide password in response
            return ResponseEntity.ok(doctor);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{doctorID}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable int doctorID) {
        int result = doctorService.deleteDoctor(doctorID);
        if (result > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
