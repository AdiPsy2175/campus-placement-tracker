package com.aditya.campusplacementtracker.controller;

import com.aditya.campusplacementtracker.entity.Student;
import com.aditya.campusplacementtracker.service.StudentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;


@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String viewStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "name") String sortBy,
            Model model) {

        Page<Student> studentPage =
                studentService.getStudentsPage(page, sortBy);

        model.addAttribute("students", studentPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", studentPage.getTotalPages());
        model.addAttribute("sortBy", sortBy);

        return "students";
    }

    @GetMapping("/add-student")
    public String showStudentForm(Model model) {

        model.addAttribute("student", new Student());

        return "add-student";
    }

    @PostMapping("/save-student")
    public String saveStudent(@ModelAttribute Student student) {

        studentService.saveStudent(student);

        return "redirect:/students";
    }

    @GetMapping("/edit-student/{id}")
    public String editStudent(@PathVariable Long id, Model model) {

        Student student = studentService.getStudentById(id);

        model.addAttribute("student", student);

        return "add-student";
    }

    @GetMapping("/delete-student/{id}")
    public String deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return "redirect:/students";
    }

    @GetMapping("/students/search")
    public String searchStudents(@RequestParam String keyword,
                                 Model model) {

        model.addAttribute("students",
                studentService.searchStudents(keyword));

        return "students";
    }

    @GetMapping("/students/placed")
    public String placedStudents(Model model) {

        model.addAttribute("students",
                studentService.getPlacedStudents());

        return "students";
    }

    @GetMapping("/students/unplaced")
    public String unplacedStudents(Model model) {

        model.addAttribute("students",
                studentService.getUnplacedStudents());

        return "students";
    }

    @GetMapping("/students/export")
    public void exportStudents(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition",
                "attachment; filename=students.csv");

        PrintWriter writer = response.getWriter();

        writer.println("ID,Name,Email,Branch,CGPA");

        for (Student student : studentService.getAllStudents()) {

            writer.println(
                    student.getId() + "," +
                            student.getName() + "," +
                            student.getEmail() + "," +
                            student.getBranch() + "," +
                            student.getCgpa()
            );
        }

        writer.flush();
        writer.close();
    }

}