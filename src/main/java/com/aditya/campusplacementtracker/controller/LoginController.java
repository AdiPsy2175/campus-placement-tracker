package com.aditya.campusplacementtracker.controller;
import com.aditya.campusplacementtracker.entity.Student;
import com.aditya.campusplacementtracker.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.aditya.campusplacementtracker.service.StudentService;
import com.aditya.campusplacementtracker.service.CompanyService;


@Controller
public class LoginController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CompanyService companyService;


    @GetMapping("/login")
    public String loginPage() {

        return "login";

    }

    @PostMapping("/login")
    public String processLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ) {

        model.addAttribute("username", username);


        model.addAttribute("studentCount",
                studentService.getStudentCount());

        model.addAttribute("companyCount",
                companyService.getCompanyCount());

        model.addAttribute("placedStudentCount",
                studentService.getPlacedStudentCount());

        return "dashboard";

    }


}