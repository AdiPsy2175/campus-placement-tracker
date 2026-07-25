package com.aditya.campusplacementtracker.controller;

import com.aditya.campusplacementtracker.service.ApplicationService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.aditya.campusplacementtracker.entity.Student;
import com.aditya.campusplacementtracker.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import com.aditya.campusplacementtracker.service.PlacementDriveService;
import com.aditya.campusplacementtracker.entity.Application;
import com.aditya.campusplacementtracker.entity.Student;
import org.springframework.security.core.Authentication;
import com.aditya.campusplacementtracker.repository.StudentRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.aditya.campusplacementtracker.entity.PlacementDrive;

@Controller
public class StudentDashboardController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private PlacementDriveService placementDriveService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/student/dashboard")
    public String studentDashboard(Authentication authentication,
                                   Model model) {

        Student student =
                studentService.getStudentByUsername(authentication.getName());

        model.addAttribute("student", student);

        return "student-dashboard";

    }

    @GetMapping("/student/drives")
    public String availableDrives(Model model,
                                  Authentication authentication) {

        String username = authentication.getName();

        Student student = studentRepository
                .findByUserUsername(username)
                .orElse(null);

        List<PlacementDrive> drives =
                placementDriveService.getAllDrives();

        Set<Long> appliedDriveIds = new HashSet<>();

        if (student != null) {

            for (PlacementDrive drive : drives) {

                if (applicationService.hasApplied(student.getId(), drive.getId())) {

                    appliedDriveIds.add(drive.getId());

                }
            }
        }

        model.addAttribute("drives", drives);
        model.addAttribute("appliedDriveIds", appliedDriveIds);

        return "student-drives";
    }

    @GetMapping("/student/applications")
    public String myApplications(Model model,
                                 Authentication authentication) {

        String username = authentication.getName();

        Student student = studentRepository
                .findByUserUsername(username)
                .orElse(null);

        if (student == null) {
            return "redirect:/login";
        }

        model.addAttribute(
                "applications",
                applicationService.getApplicationsForStudent(student.getId())
        );

        return "student-applications";
    }

    @PostMapping("/student/apply/{driveId}")
    public String applyForDrive(@PathVariable Long driveId,
                                Authentication authentication) {

        String username = authentication.getName();

        Student student = studentRepository
                .findByUserUsername(username)
                .orElse(null);

        if (student == null) {
            return "redirect:/student/drives";
        }

        Application application = new Application();

        application.setStudentId(student.getId());
        application.setDriveId(driveId);
        application.setStatus("Pending");

        applicationService.saveApplication(application);

        return "redirect:/student/drives";
    }

}