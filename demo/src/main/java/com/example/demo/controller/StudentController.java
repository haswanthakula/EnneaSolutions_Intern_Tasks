package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.repo.StudentRepo;



@RestController
public class StudentController {

    @Autowired
    StudentRepo studentRepo;

    @PostMapping("/api/students")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
         return new ResponseEntity<>(studentRepo.save(student),HttpStatus.CREATED);
    }
    
    @GetMapping("/api/students")
    public ResponseEntity<List<Student>> getStudents(){
        return new ResponseEntity<>(studentRepo.findAll(),HttpStatus.OK);
    }


}
