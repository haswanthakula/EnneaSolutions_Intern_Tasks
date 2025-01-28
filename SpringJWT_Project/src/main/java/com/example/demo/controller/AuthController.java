package com.example.demo.controller;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegistrationRequestDTO;
import com.example.demo.dto.SuccessResponseDTO;
import com.example.demo.entity.Student;
import com.example.demo.repo.StudentRepo;
import com.example.demo.security.JwtUtils;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            logger.info("Login attempt for email: {}", loginRequest.getEmail());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            Student student = studentRepo.findByStudentEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("Error: Student not found."));

            logger.info("Login successful for email: {}", loginRequest.getEmail());

            Map<String, Object> response = new HashMap<>();
            response.put("token", jwt);
            response.put("id", student.getId());
            response.put("email", student.getStudentEmail());
            response.put("message", "Login successful");

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            logger.warn("Login failed for email: {}", loginRequest.getEmail());
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (Exception e) {
            logger.error("Login error for email: {}", loginRequest.getEmail(), e);
            Map<String, String> response = new HashMap<>();
            response.put("message", "An error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<SuccessResponseDTO> registerUser(@Valid @RequestBody RegistrationRequestDTO registrationRequest) {
        try {
            // Validate password confirmation
            if (!registrationRequest.getPassword().equals(registrationRequest.getConfirmPassword())) {
                throw new IllegalArgumentException("Passwords do not match");
            }

            if (studentRepo.existsByStudentEmail(registrationRequest.getStudentEmail())) {
                throw new IllegalArgumentException("Email is already in use!");
            }

            // Create new student
            Student student = new Student();
            student.setStudentName(registrationRequest.getStudentName());
            student.setStudentEmail(registrationRequest.getStudentEmail());
            student.setStudentAddress(registrationRequest.getStudentAddress());
            student.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            student.setEnabled(true);

            Student savedStudent = studentRepo.save(student);

            SuccessResponseDTO response = new SuccessResponseDTO(
                "Student registered successfully!", 
                savedStudent
            );

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            SuccessResponseDTO errorResponse = new SuccessResponseDTO(
                e.getMessage(), 
                null
            );
            return ResponseEntity.badRequest().body(errorResponse);
        } catch (Exception e) {
            SuccessResponseDTO errorResponse = new SuccessResponseDTO(
                "Registration failed: " + e.getMessage(), 
                null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
