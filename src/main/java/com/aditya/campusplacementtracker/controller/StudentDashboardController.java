package com.aditya.campusplacementtracker.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentDashboardController {

    @GetMapping("/student/dashboard")
    public String studentDashboard(Authentication authentication,
                                   Model model) {

        model.addAttribute("username",
                authentication.getName());

        return "student-dashboard";
    }

}