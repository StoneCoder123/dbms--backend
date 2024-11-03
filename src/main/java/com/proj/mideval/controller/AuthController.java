package com.proj.mideval.controller;

import com.proj.mideval.model.Chemist;
import com.proj.mideval.model.Patient;
import com.proj.mideval.model.Doctor;
import com.proj.mideval.model.Admin;
import com.proj.mideval.service.AdminService;
import com.proj.mideval.service.ChemistService;
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

    @Autowired
    private AdminService adminService;

    @Autowired
    private ChemistService chemistService;

    @GetMapping("/userinfo")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String token) {
        String email = JwtUtil.extractEmail(token);
        String userType = JwtUtil.extractUserType(token);
        Map<String, Object> userDetails = new HashMap<>();

        switch (userType.toUpperCase()) {
            case "PATIENT":
                Optional<Patient> patientOpt = patientService.getPatientByEmail(email);
                if (patientOpt.isPresent()) {
                    Patient patient = patientOpt.get();
                    userDetails.put("firstName", patient.getFirstName());
                    userDetails.put("lastName", patient.getLastName());
                    userDetails.put("email", patient.getEmail());
                    userDetails.put("userType", "PATIENT");
                    userDetails.put("ntk", patient.getNTK());
                    userDetails.put("history", patient.getHistory());
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
                }
                break;

            case "DOCTOR":
                Optional<Doctor> doctorOpt = doctorService.getDoctorByEmail(email);
                if (doctorOpt.isPresent()) {
                    Doctor doctor = doctorOpt.get();
                    userDetails.put("firstName", doctor.getFirstName());
                    userDetails.put("lastName", doctor.getLastName());
                    userDetails.put("email", doctor.getEmail());
                    userDetails.put("userType", "DOCTOR");
                    userDetails.put("specialization", doctor.getSpecialization());
                    userDetails.put("department", doctor.getDepartment());
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
                }
                break;

            case "ADMIN":
                Optional<Admin> adminOpt = adminService.getAdminByEmail(email);
                if (adminOpt.isPresent()) {
                    Admin admin = adminOpt.get();
                    userDetails.put("email", admin.getEmail());
                    userDetails.put("userType", "ADMIN");
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
                }
                break;

            case "CHEMIST":
                Optional<Chemist> chemistOpt = chemistService.getChemistByEmail(email);
                if (chemistOpt.isPresent()) {
                    Chemist chemist = chemistOpt.get();
                    userDetails.put("firstName", chemist.getFirstName());
                    userDetails.put("lastName", chemist.getLastName());
                    userDetails.put("email", chemist.getEmail());
                    userDetails.put("userType", "CHEMIST");
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chemist not found");
                }
                break;

            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user type");
        }

        return ResponseEntity.ok(userDetails);
    }

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
                String id = doctorService.getDoctorIDByEmail(email).toString();
                response.put("userType", "DOCTOR");
                response.put("token", token);
                response.put("email", email);
                response.put("id", id);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid doctor credentials");
            }
        } else if ("ADMIN".equalsIgnoreCase(userType)) {
            Optional<Admin> adminOpt = adminService.authenticateAdmin(email, password);
            if (adminOpt.isPresent()) {
                token = JwtUtil.generateToken(email, "ADMIN");
                String id = adminService.getAdminIDByEmail(email).toString();
                response.put("userType", "ADMIN");
                response.put("token", token);
                response.put("email", email);
                response.put("id", id);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid admin credentials");
            }
        } else if ("CHEMIST".equalsIgnoreCase(userType)) {
            Optional<Chemist> chemist = chemistService.authenticateChemist(email, password);
            if (chemist.isPresent()) {
                token = JwtUtil.generateToken(email, "CHEMIST");
                String id = chemistService.getChemistIDByEmail(email).toString();
                response.put("userType", "CHEMIST");
                response.put("token", token);
                response.put("email", email);
                response.put("id", id);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid chemist credentials");
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user type");
        }
    }
}
