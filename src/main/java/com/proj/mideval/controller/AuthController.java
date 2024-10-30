package com.proj.mideval.controller;

import com.proj.mideval.model.Patient;
import com.proj.mideval.model.Doctor;
import com.proj.mideval.service.PatientService;
import com.proj.mideval.service.DoctorService;
import com.proj.mideval.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password, @RequestParam String userType) {
        String token;
        Map<String, String> response = new HashMap<>();

        if ("PATIENT".equalsIgnoreCase(userType)) {
            Optional<Patient> patient = patientService.authenticatePatient(email, password);
            if (patient.isPresent()) {
                token = JwtUtil.generateToken(email, "PATIENT");
                String id = patientService.getPatientIdByEmail(email).toString();
                response.put("userType", "PATIENT");
                response.put("token", token);
                response.put("email", email);
                response.put("id", id);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid patient credentials");
            }

        } else if ("DOCTOR".equalsIgnoreCase(userType)) {
            Optional<Doctor> doctor = doctorService.authenticateDoctor(email, password);
            if (doctor.isPresent()) {
                token = JwtUtil.generateToken(email, "DOCTOR");
                response.put("userType", "DOCTOR");
                response.put("token", token);
                response.put("email", email);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid doctor credentials");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user type");
        }
    }
}
