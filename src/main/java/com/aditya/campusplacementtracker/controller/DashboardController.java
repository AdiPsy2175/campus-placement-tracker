package com.aditya.campusplacementtracker.controller;

import com.aditya.campusplacementtracker.service.ApplicationService;
import com.aditya.campusplacementtracker.service.CompanyService;
import com.aditya.campusplacementtracker.service.PlacementDriveService;
import com.aditya.campusplacementtracker.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
@Controller
public class DashboardController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private PlacementDriveService placementDriveService;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("studentCount", studentService.getStudentCount());
        model.addAttribute("companyCount", companyService.getCompanyCount());
        model.addAttribute("driveCount", placementDriveService.getDriveCount());
        model.addAttribute("applicationCount", applicationService.getApplicationCount());
        model.addAttribute("pendingCount",
                applicationService.getPendingApplicationCount());

        model.addAttribute("selectedCount",
                applicationService.getSelectedApplicationCount());

        model.addAttribute("rejectedCount",
                applicationService.getRejectedApplicationCount());
        return "dashboard";
    }
}