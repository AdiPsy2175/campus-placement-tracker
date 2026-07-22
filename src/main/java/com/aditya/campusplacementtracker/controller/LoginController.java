package com.aditya.campusplacementtracker.controller;
import com.aditya.campusplacementtracker.entity.Student;
import com.aditya.campusplacementtracker.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    @Autowired
    private StudentService studentService;

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

        Student student = new Student();

        student.setName("Aditya");
        student.setEmail("aditya@gmail.com");
        student.setBranch("CSE");
        student.setCgpa(8.9);
        student.setPlaced(false);

        studentService.saveStudent(student);

        return "dashboard";

    }

}