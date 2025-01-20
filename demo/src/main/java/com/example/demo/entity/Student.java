package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String studentName;
    private String studentEmail;
    private String studentAddress;

    public Student(long id, String studentAddress, String studentEmail, String studentName) {
        this.id = id;
        this.studentAddress = studentAddress;
        this.studentEmail = studentEmail;
        this.studentName = studentName;
    }

    public Student() {
        super();
    }

    

    public long getId() {
        return id;
    }
    public void setId(long id) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student{");
        sb.append("id=").append(id);
        sb.append(", studentName=").append(studentName);
        sb.append(", studentEmail=").append(studentEmail);
        sb.append(", studentAddress=").append(studentAddress);
        sb.append('}');
        return sb.toString();
    }

    

}
