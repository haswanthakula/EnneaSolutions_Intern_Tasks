package com.example.demo.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentEmail(String email);
    boolean existsByStudentEmail(String email);
}
