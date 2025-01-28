package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.dto.StudentResponseDTO;
import com.example.demo.entity.Student;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.repo.StudentRepo;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @PostMapping
    public ResponseEntity<ApiResponse> saveStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        // Validate password match
        if (!studentRequestDTO.isPasswordMatching()) {
            return ResponseEntity
                .badRequest()
                .body(new ApiResponse(false, "Passwords do not match", null, HttpStatus.BAD_REQUEST));
        }

        // Create new student
        Student student = new Student();
        student.setStudentName(studentRequestDTO.getStudentName());
        student.setStudentEmail(studentRequestDTO.getStudentEmail());
        student.setPassword(passwordEncoder.encode(studentRequestDTO.getPassword()));

        Student savedStudent = studentRepo.save(student);
        StudentResponseDTO responseDTO = new StudentResponseDTO(savedStudent);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ApiResponse(true, "Student created successfully", responseDTO, HttpStatus.CREATED));
    }
    
    @GetMapping
    public ResponseEntity<?> getStudents() {
        List<StudentResponseDTO> students = studentRepo.findAll().stream()
            .map(StudentResponseDTO::new)
            .collect(Collectors.toList());

        // Return students directly for dashboard compatibility
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getStudent(@PathVariable long id) {
        Student student = studentRepo.findById(id)
            .orElseThrow(() -> new StudentNotFoundException(id));

        StudentResponseDTO responseDTO = new StudentResponseDTO(student);
        return ResponseEntity.ok(
            new ApiResponse(true, "Student retrieved successfully", responseDTO, HttpStatus.OK)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateStudent(
        @PathVariable long id, 
        @Valid @RequestBody StudentRequestDTO studentRequestDTO
    ) {
        Student existingStudent = studentRepo.findById(id)
            .orElseThrow(() -> new StudentNotFoundException(id));

        existingStudent.setStudentName(studentRequestDTO.getStudentName());
        existingStudent.setStudentEmail(studentRequestDTO.getStudentEmail());
        
        // Update address if provided
        if (studentRequestDTO.getStudentAddress() != null) {
            existingStudent.setStudentAddress(studentRequestDTO.getStudentAddress());
        }

        // Only update password if provided and matches confirm password
        if (studentRequestDTO.getPassword() != null && studentRequestDTO.isPasswordMatching()) {
            existingStudent.setPassword(passwordEncoder.encode(studentRequestDTO.getPassword()));
        }

        Student updatedStudent = studentRepo.save(existingStudent);
        StudentResponseDTO responseDTO = new StudentResponseDTO(updatedStudent);

        return ResponseEntity.ok(
            new ApiResponse(true, "Student updated successfully", responseDTO, HttpStatus.OK)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable long id) {
        try {
            Student student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found"));

            logger.info("Attempting to delete student with ID: {}", id);
            studentRepo.delete(student);
            logger.info("Successfully deleted student with ID: {}", id);

            return ResponseEntity.ok(
                new ApiResponse(true, "Student deleted successfully", null, HttpStatus.OK)
            );
        } catch (StudentNotFoundException e) {
            logger.warn("Attempted to delete non-existent student with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse(false, e.getMessage(), null, HttpStatus.NOT_FOUND));
        } catch (DataIntegrityViolationException e) {
            logger.error("Error deleting student with ID: {} - Constraint violation", id, e);
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse(false, "Cannot delete student due to existing dependencies", null, HttpStatus.CONFLICT));
        } catch (Exception e) {
            logger.error("Unexpected error deleting student with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(false, "An unexpected error occurred", null, HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
