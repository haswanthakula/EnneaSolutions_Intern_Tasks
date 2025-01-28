package com.example.demo.dto;

import com.example.demo.entity.Student;

public class StudentResponseDTO {
    private Long id;
    private String studentName;
    private String studentEmail;
    private String studentAddress;

    public StudentResponseDTO() {}

    public StudentResponseDTO(Student student) {
        this.id = student.getId();
        this.studentName = student.getStudentName();
        this.studentEmail = student.getStudentEmail();
        this.studentAddress = student.getStudentAddress();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }
}
