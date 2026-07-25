package com.aditya.campusplacementtracker.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.aditya.campusplacementtracker.entity.Student;
import com.aditya.campusplacementtracker.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class StudentDashboardController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/student/dashboard")
    public String studentDashboard(Authentication authentication,
                                   Model model) {

        Student student =
                studentService.getStudentByUsername(authentication.getName());

        model.addAttribute("student", student);

        return "student-dashboard";

    }

}