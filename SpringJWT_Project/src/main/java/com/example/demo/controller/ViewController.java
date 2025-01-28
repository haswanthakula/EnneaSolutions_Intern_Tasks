package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {

    @GetMapping({"/", "/login"})
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/student/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        model.addAttribute("studentId", id);
        return "students/edit";
    }

    @GetMapping("/students/add")
    public String addStudent() {
        return "students/add";
    }
}
